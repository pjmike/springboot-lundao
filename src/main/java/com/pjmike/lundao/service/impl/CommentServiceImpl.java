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
    private static Map<Integer, MultableInt> map = new HashMap<>();
    private static Map<Integer, CountVector> mapCount = new HashMap<>();

    @Override
    public Comment save(Comment model) {
        //计数器
//        MultableInt counter = map.get(model.getThesisId());
//        if (counter == null) {
//            MultableInt newCounter = new MultableInt();
//            map.put(model.getThesisId(), newCounter);
//            model.setId(newCounter.getCount());
//        } else {
//            counter.increment();
//            log.info("论点 计数器 {} 的 counter +1后的值为 :{}",model.getTargetId(),counter.getCount());
//            model.setId(counter.getCount());
//        }
        //用redis做计数器
        Object counter = redisOperator.get("counter-" + model.getThesisId());
        if (counter == null) {
            redisOperator.incr("counter-" + model.getThesisId(), 1);
            model.setId(1);
        } else {
            counter = redisOperator.incr("counter-" + model.getThesisId(), 1);
            log.info("论点计数器 {} 的counter值 ：{}",model.getThesisId(),counter);
            counter = redisOperator.get("counter-" + model.getThesisId());
            model.setId((Integer) counter);
        }
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
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<Comment> getFinalComments(CommentVo commentVo) {
        //待返回的对话列表
        LinkedList<Comment> result = new LinkedList<>();
        //某个文章下所有的评论回复数
        List<Comment> commentList = getAllCommentsByThesisId(commentVo.getThesisId());
        log.info("某个文章下所有的评论回复数： {}", commentList);
        //某文章总的评论回复的点赞情况
        List<Likecount> likecounts = likeCountSerivce.getLikeCountsByType(3);
        log.info("某文章总的评论回复的点赞情况: {}", likecounts);
        //某一个评论回复下的点赞情况
        List<Likecount> childCounts = new LinkedList<>();
        //对文章的回复，targetId为0,如果不为0，则是对评论/回复的回复
        if (commentVo.getTargetId() != 0) {
            CountVector countVector = mapCount.get(commentVo.getThesisId());
            log.info("countVector:{}", countVector);
            if (countVector != null) {
                List<Integer> integers = countVector.map.get(commentVo.getTargetId());
                for (int i = 0; i < likecounts.size(); i++) {
                    for (int j = 0; j < integers.size(); j++) {
                        if (likecounts.get(i).getTypeId().equals(integers.get(j).intValue())) {
                            //找出某一评论/回复下所有回复的点赞情况
                            childCounts.add(likecounts.get(i));
                        }
                    }
                }
            }
            log.info("找出某一评论/回复下所有回复的点赞情况: {}", childCounts);
            likecounts = childCounts;
        }
        //ArrayList转为LinedList
        LinkedList<Likecount> linkedList = new LinkedList<>(likecounts);
        //对点赞数进行降序排序,点赞数最大的排在第一位
        Collections.sort(linkedList, new LikeCountComparator());
        //迭代器，linkedList迭代器可以向前向后
        Iterator<Likecount> iterator = linkedList.listIterator();
        //找出某一篇文章下所有评论回复的编号编排情况(邻接表的形式)
        CountVector vector = mapCount.get(commentVo.getThesisId());
        if (iterator.hasNext()) {
            Likecount likecount = iterator.next();
            //向右滑或者左滑
            if (commentVo.isLeft() || commentVo.isRight()) {
                //向右滑
                log.info("左滑或者右滑");
                if (commentVo.isRight()) {
                    if (iterator.hasNext()) {
                        likecount = iterator.next();
                    } else {
                        log.info("右滑没有数据了");
                        return null;
                    }
                }
                //向左滑
                if (commentVo.isLeft()) {
                    if (((ListIterator<Likecount>) iterator).hasPrevious()) {
                        likecount = ((ListIterator<Likecount>) iterator).previous();
                    } else {
                        log.info("左滑没有数据了");
                        return null;
                    }
                }
            }
            //dfs找出一个对话列表
            dfs(commentVo.getThesisId(), likecount.getTypeId(), commentList, result, vector);
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
        CountVector vector = mapCount.get(model.getThesisId());
        LinkedList<Integer> linkedList;
        if (vector == null) {
            //创建文章id下的回复列表
            vector = new CountVector();
            linkedList = new LinkedList<>();
            linkedList.add(model.getId());
            vector.map.put(model.getTargetId(), linkedList);
            mapCount.put(model.getThesisId(), vector);
        } else {
            //目标id下的回复列表
            linkedList = vector.map.get(model.getTargetId());
            if (linkedList == null) {
                //新建列表
                linkedList = new LinkedList<>();
                linkedList.add(model.getId());
                vector.map.put(model.getTargetId(), linkedList);
            } else {
                linkedList.add(model.getId());
                vector.map.put(model.getTargetId(), linkedList);
            }
        }
    }

    private void dfs(int x, int id, List<Comment> commentList, LinkedList<Comment> linkedList, CountVector vector) {
        String to_name = commentList.get(id).getToUname();
        String from_name = commentList.get(id).getFromUname();
        linkedList.add(commentList.get(id));
        System.out.println(from_name + " say: " + commentList.get(id).getContent() + " to: " + to_name);
        for (int i = 0; i < vector.map.get(id).size(); i++) {
            Integer index = vector.map.get(id).get(i);
            if (commentList.get(index).getFromUname().equals(to_name)) {
                dfs(id, index, commentList, linkedList, vector);
            }
        }
    }
}
