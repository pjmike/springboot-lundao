package com.pjmike.lundao.Task;

import com.pjmike.lundao.dao.DebateMapper;
import com.pjmike.lundao.dao.LikeMapper;
import com.pjmike.lundao.dao.LikecountMapper;
import com.pjmike.lundao.dao.ThesisMapper;
import com.pjmike.lundao.domain.Favour;
import com.pjmike.lundao.domain.Likecount;
import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.utils.enums.TypeEnum;
import com.pjmike.lundao.utils.redis.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 定时任务
 *
 * @author pjmike
 * @create 2018-05-26 17:54
 */
@Component
@Slf4j
public class Task {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    @Autowired
    private RedisKeyUtils redisKeyUtils;
    @Autowired
    private DebateMapper debateMapper;
    @Autowired
    private LikecountMapper likecountMapper;
    @Autowired
    private ThesisMapper thesisMapper;
    @Autowired
    private LikeMapper likeMapper;

    /**
     * 更新thesis的点赞情况
     */
    @Scheduled(cron = "0 0 0-2 * * ?")
    public void redisTask() {
        log.info("现在时间:" + simpleDateFormat.format(new Date())+"开始执行点赞数刷回数据库操作");
        //对所有论点进行操作
        List<Thesis> thesisList = thesisMapper.selectAll();
        for (Thesis thesis : thesisList) {
            Integer integer = redisKeyUtils.getIncrement(TypeEnum.THESIS.getType(), thesis.getThesisId());
            log.info("目前论点 {} 的点赞数为 {}",thesis.getThesisId(),integer);
            if (integer != null) {
                //更新thesis表的点赞数
                thesis.setFavour(integer);
                //更新thesis数据库
                thesisMapper.updateByPrimaryKeySelective(thesis);
                //更新点赞详情表
                Likecount likecount = new Likecount(thesis.getThesisId(),TypeEnum.THESIS.getType(),integer);
                if (likecountMapper.selectByTypeAndTypeId(TypeEnum.THESIS.getType(),thesis.getThesisId())  == null) {
                    //插入点赞数
                    likecountMapper.insert(likecount);
                } else {
                    //更新点赞数
                    likecountMapper.updateCount(likecount);
                }
            }
        }
    }

    /**
     * 每天
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void setFavour() {
        log.info("现在时间"+simpleDateFormat.format(new Date())+"开始执行刷回点赞对象到数据库");
        Set<Object> postIds = redisKeyUtils.getPostId(1);
        for (Object postId : postIds) {
            Set<Object> userIds = redisKeyUtils.getUserIdByPost(1, (Integer) postId);
            for (Object userId : userIds) {
                Favour favour = (Favour) redisKeyUtils.hget(1, (Integer)postId, (Integer)userId);
                if (likeMapper.selectOneFavour(favour) != null) {
                    likeMapper.updateLike(favour);
                } else {
                    likeMapper.insert(favour);
                }
            }
        }
    }
}
