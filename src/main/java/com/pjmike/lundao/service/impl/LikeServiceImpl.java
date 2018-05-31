package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.LikeMapper;
import com.pjmike.lundao.domain.Favour;
import com.pjmike.lundao.service.LikeService;
import com.pjmike.lundao.utils.redis.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-05-26 14:51
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RedisKeyUtils redisKeyUtils;
    @Autowired
    private LikeMapper likeMapper;
    @Override
    public Favour save(Favour model) {
        Favour favour = (Favour) redisKeyUtils.hget(model.getType(), model.getTypeId(), model.getUid());
        //查询redis中是否存有
        if (favour != null) {
            //如果原来是点赞，现在是取消赞，点赞数减一
            if (favour.getStatus() == 1 && model.getStatus() == 0) {
                redisKeyUtils.subCounter(model.getType(),model.getTypeId());
            }
            //如果原来是没有点赞，现在是点赞，点赞数+1
            if (favour.getStatus() == 0 && model.getStatus() == 1) {
                redisKeyUtils.setCounter(model.getType(),model.getTypeId());
            }
            favour.setStatus(model.getStatus());
            redisKeyUtils.hset(model.getType(),model.getTypeId(),model.getUid(), favour);
        } else {
            //没有点赞，则插入数据库和redis,并点赞数+1
            likeMapper.insert(model);
            redisKeyUtils.hset(model.getType(),model.getTypeId(),model.getUid(),model);
            redisKeyUtils.setCounter(model.getType(), model.getTypeId());
            //以下操作便于redis将数据刷回数据库
            //将被点赞的文章写到一个type_set的set集合里
            redisKeyUtils.set(model.getType(), model.getTypeId());
            //将每篇点赞文章的uid写到set里
            redisKeyUtils.set(model.getType(),model.getTypeId(),model.getUid());
        }
        return model;
    }

    @Override
    public Integer getLikeCount(Integer type, Integer typeId) {
        return redisKeyUtils.getIncrement(type, typeId);
    }
}
