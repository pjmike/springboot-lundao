//package com.pjmike.lundao.Task;
//
//import com.pjmike.lundao.dao.LikeMapper;
//import com.pjmike.lundao.dao.LikecountMapper;
//import com.pjmike.lundao.domain.Favour;
//import com.pjmike.lundao.domain.Likecount;
//import com.pjmike.lundao.utils.redis.RedisKeyUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Set;
//
///**
// * 定时任务
// *
// * @author pjmike
// * @create 2018-05-26 17:54
// */
//@Component
//@Slf4j
//public class Task {
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//    @Autowired
//    private RedisKeyUtils redisKeyUtils;
//    @Autowired
//    private LikecountMapper likecountMapper;
//    @Autowired
//    private LikeMapper likeMapper;
//    @Scheduled(cron = "0 0 0/1 * * ? *")
//    public void redisTask() {
//        log.info("现在时间:" + simpleDateFormat.format(new Date())+"开始执行点赞数刷回数据库操作");
//        Integer integer = redisKeyUtils.getIncrement(1, 2);
//        Likecount likecount = new Likecount(2,1,integer);
//        if (likecountMapper.selectByTypeAndTypeId(1,2)  == null) {
//            likecountMapper.insert(likecount);
//        } else {
//            likecountMapper.updateCount(likecount);
//        }
//
//    }
//
//    /**
//     * 每天
//     */
//    @Scheduled(cron = "0 0 0 1/1 * ? *")
//    public void setFavour() {
//        log.info("现在时间"+simpleDateFormat.format(new Date())+"开始执行刷回点赞对象到数据库");
//        Set<Object> postIds = redisKeyUtils.getPostId(1);
//        for (Object postId : postIds) {
//            Set<Object> userIds = redisKeyUtils.getUserIdByPost(1, (Integer) postId);
//            for (Object userId : userIds) {
//                Favour favour = (Favour) redisKeyUtils.hget(1, (Integer)postId, (Integer)userId);
//                if (likeMapper.selectOneFavour(favour) != null) {
//                    likeMapper.updateLike(favour);
//                } else {
//                    likeMapper.insert(favour);
//                }
//            }
//        }
//    }
//}
