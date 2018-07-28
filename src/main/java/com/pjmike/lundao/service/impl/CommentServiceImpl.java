package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.CommentMapper;
import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.Likecount;
import com.pjmike.lundao.domain.vo.CommentVo;
import com.pjmike.lundao.domain.vo.CountVector;
import com.pjmike.lundao.domain.vo.LikeCountComparator;
import com.pjmike.lundao.domain.vo.MultableInt;
import com.pjmike.lundao.service.CommentService;
import com.pjmike.lundao.service.LikeCountSerivce;
import com.pjmike.lundao.utils.constants.Constants;
import com.pjmike.lundao.utils.redis.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-30 9:46
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikeCountSerivce likeCountSerivce;
    private static Map<Integer, CountVector> mapCount = new HashMap<>();

    @Override
    public Comment save(Comment model) {
        //用redis做计数器
        Object counter = redisOperator.get("counter-" + model.getThesisId());
        if (counter == null) {
            redisOperator.incr("counter-" + model.getThesisId(), 1);
            model.setId(1);
        } else {
            counter = redisOperator.incr("counter-" + model.getThesisId(), 1);
            log.info("论点计数器 {} 的counter值 ：{}", model.getThesisId(), counter);
            counter = redisOperator.get("counter-" + model.getThesisId());
            model.setId((Integer) counter);
        }
        //设置创建时间
        model.setCreateTime(new Date());
        //设置默认点赞数为0
        model.setFavour(0);
        commentMapper.insert(model);
        //编排编号
        divideNumber(model);

        return model;
    }

    @Override
    public List<Comment> getAllCommentsByThesisId(Integer thesisId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("thesisId", thesisId);
        log.info("comments :{}",commentMapper.selectByExample(example));
        return commentMapper.selectByExample(example);
    }

    /**
     * thesis里的一个回复列表
     *
     * @param thesisId
     * @return
     */
    @Override
    public List<Comment> getCommentForThesis(Integer thesisId) {
        //待返回的对话列表
        LinkedList<Comment> result = new LinkedList<>();
        //某个文章下所有的评论回复数
        List<Comment> commentList = getAllCommentsByThesisId(thesisId);
        List<Comment> childCounts = new LinkedList<>();
        for (Comment comment : commentList) {
            if (comment.getTargetId().equals(0)) {
                childCounts.add(comment);
            }
        }
        log.info("直接对论点的回复: {},回复数：{}",childCounts,childCounts.size());
        //ArrayList转为LinedList
        LinkedList<Comment> linkedList = new LinkedList<>(childCounts);
        //对点赞数进行降序排序,点赞数最大的排在第一位
        Collections.sort(linkedList, new LikeCountComparator());
        log.info("排序后的linkedList : {}", linkedList);
        //迭代器，linkedList迭代器可以向前向后
        Iterator<Comment> iterator = linkedList.listIterator();
        if (iterator.hasNext()) {
            Comment comment = iterator.next();
            log.info("comment:{}",comment);
            dfs(0,comment.getId(),commentList,result);
        }
        return result;
    }

    @Override
    public List<Comment> getFinalComments(CommentVo commentVo) {
        //待返回的对话列表
        LinkedList<Comment> result = new LinkedList<>();
        //某个文章下所有的评论回复数
        List<Comment> commentList = getAllCommentsByThesisId(commentVo.getThesisId());
        List<Comment> childCounts = new LinkedList<>();
        //对文章的回复，targetId为0,如果不为0，则是对评论/回复的回复
        if (commentVo.getTargetId() != 0) {
            CountVector countVector = (CountVector) redisOperator.hget(Constants.COMMET_HASH_SET, commentVo.getThesisId().toString());
            log.info("countVector:{}", countVector);
            log.info("countVector.map.List: {}", CountVector.map.containsKey(commentVo.getTargetId()));
            if (countVector != null) {
                if (CountVector.map.containsKey(commentVo.getTargetId())) {
                    List<Integer> integers = CountVector.map.get(commentVo.getTargetId());
                    log.info("intergers : {}", integers);
                    for (int i = 0; i < commentList.size(); i++) {
                        for (int j = 0; j < integers.size(); j++) {
                            if (commentList.get(i).getTargetId().equals(integers.get(j).intValue())) {
                                //找出某一评论/回复下所有回复的点赞情况
                                integers.add(commentList.get(i).getId());
                            }
                        }
                    }
                    for (int i = 0; i < commentList.size(); i++) {
                        for (int j = 0; j < integers.size(); j++) {
                            if (commentList.get(i).getId().equals(integers.get(j))) {
                                childCounts.add(commentList.get(i));
                            }
                        }
                    }
                }
            }
            log.info("找出某一评论/回复下所有回复的点赞情况: {}", childCounts);
            commentList = childCounts;
        }
        LinkedList<Comment> comments = new LinkedList<>();
        for (Comment comment1 : commentList) {
            if (comment1.getTargetId().equals(commentVo.getTargetId())) {
                comments.add(comment1);
            }
        }
        //ArrayList转为LinedList
        LinkedList<Comment> linkedList = new LinkedList<>(comments);
        //对点赞数进行降序排序,点赞数最大的排在第一位
        Collections.sort(linkedList, new LikeCountComparator());
        log.info("排序后的linkedList : {}", linkedList);
        //迭代器，linkedList迭代器可以向前向后
        Iterator<Comment> iterator = linkedList.listIterator();
        //找出某一篇文章下所有评论回复的编号编排情况(邻接表的形式)
        CountVector vector = (CountVector) redisOperator.hget(Constants.COMMET_HASH_SET, commentVo.getThesisId().toString());
        log.info(" 某一篇文章下所有评论回复的编排情况: vector : {} ", vector);
        if (iterator.hasNext()) {
            Comment comment = iterator.next();
            //向右滑或者左滑
            if (commentVo.isLeft() || commentVo.isRight()) {
                //向右滑
                log.info("左滑或者右滑");
                if (commentVo.isRight()) {
                    if (iterator.hasNext()) {
                        comment = iterator.next();
                    } else {
                        log.info("右滑没有数据了");
                        return null;
                    }
                }
                //向左滑
                if (commentVo.isLeft()) {
                    if (((ListIterator<Comment>) iterator).hasPrevious()) {
                        comment = ((ListIterator<Comment>) iterator).previous();
                    } else {
                        log.info("左滑没有数据了");
                        return null;
                    }
                }
            }

            //dfs找出一个对话列表
            if (vector != null || comment != null) {
                //第二个参数是对第一个的回复
                dfs(commentVo.getTargetId(), comment.getId(), commentList, result);
            }
        }
        //输出结果:
        log.info("result: {}", result);
        return result;
    }

    /**
     * 根据comment编排编号
     *
     * @param model
     */
    private void divideNumber(Comment model) {
        //TODO,类似H[i][j]存储编号关系,mapCount存储一个文章id下所有的回复编号
        //TODO,CountVector中的map的key-value指:一个目标id下的所有回复
        CountVector vector = (CountVector) redisOperator.hget(Constants.COMMET_HASH_SET, model.getThesisId().toString());
        log.info("vector : {}", vector);
        LinkedList<Integer> linkedList;
        if (vector == null) {
            //创建文章id下的回复列表
            vector = new CountVector();
            linkedList = new LinkedList<>();
            linkedList.add(model.getId());
            log.info("linkedList :{}", linkedList);
            CountVector.map.put(model.getTargetId(), linkedList);
            redisOperator.hset(Constants.COMMET_HASH_SET, model.getThesisId().toString(), vector);
        } else {
            //目标id下的回复列表
            linkedList = CountVector.map.get(model.getTargetId());
            log.info("vector != null and linkedList is {} from  key : {}", linkedList, model.getTargetId());
            if (linkedList == null) {
                //新建列表
                linkedList = new LinkedList<>();
                linkedList.add(model.getId());
                CountVector.map.put(model.getTargetId(), linkedList);
            } else {
                linkedList.add(model.getId());
                log.info("vector != null and linkedList2 is {} from  key : {}", linkedList, model.getTargetId());
                CountVector.map.put(model.getTargetId(), linkedList);
                log.info("map :{}", CountVector.map);
            }
        }
    }

    private void dfs(int x, int id, List<Comment> commentList, LinkedList<Comment> linkedList) {
        //id对x回复
        //TODO 要从commentList里拿id值为参数id的Comment.
        //TODO 下面是错误的写法，不能从commentList的index列表拿到
        Comment comment = null;
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getId().equals(id)) {
                comment = commentList.get(i);
                log.info("找到的comment: {}",comment);
                break;
            }
        }
        String to_name = comment .getToUname();
        String from_name = comment .getFromUname();
        //将辩题下点赞数第一的评论或者最新发表的评论加入列表中
        linkedList.add(comment);
        log.info("new LinkedList result : {} ", linkedList);
        System.out.println(from_name + " say: " + comment.getContent() + " to: " + to_name);

        //TODO 加入该评论下的回复
        if (CountVector.map.containsKey(id)) {
            for (int i = 0; i < CountVector.map.get(id).size(); i++) {
                Integer index = CountVector.map.get(id).get(i);
                for (int j = 0; j < commentList.size(); j++) {
                    if (commentList.get(j).getId().equals(index)) {
                        comment = commentList.get(j);
                        if (comment.getFromUname().equals(to_name)) {
                            dfs(id, index, commentList, linkedList);
                        }
                    }
                }
            }
        }

    }
}
