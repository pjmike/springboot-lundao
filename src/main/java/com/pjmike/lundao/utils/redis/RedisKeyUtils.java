package com.pjmike.lundao.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 点赞相关操作
 *
 * @author pjmike
 * @create 2018-05-26 15:02
 */
@Component
public class RedisKeyUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用hash存储点赞对象
     *
     * @param type
     * @param typeId
     * @param userId
     * @param value
     */
    public void hset(Integer type, Integer typeId, Integer userId, Object value) {
        redisTemplate.opsForHash().put(String.valueOf(type), type + "_" + typeId + "_user_like_" + userId, value);
    }

    /**
     *
     *
     * @param type
     * @param typeId 获取点赞记录
     * @param userId
     * @return
     */
    public Object hget(Integer type, Integer typeId, Integer userId) {
        return redisTemplate.opsForHash().get(String.valueOf(type), type + "_" + typeId + "_user_like_" + userId);

    }
    /**
     * 计数器
     *
     * @param type
     * @param typeId
     */
    public void setCounter(Integer type, Integer typeId) {
        redisTemplate.opsForValue().increment(String.valueOf(type) + "_" + String.valueOf(typeId) + "_counter", 1);
    }

    public void subCounter(Integer type, Integer typeId) {
        Integer count = (Integer) redisTemplate.opsForValue().get(String.valueOf(type) + "_" + String.valueOf(typeId)+"_counter");
        System.out.println(count);
        if (count == 0) {
            redisTemplate.opsForValue().set(String.valueOf(type) + "_" + String.valueOf(typeId)+"_counter",0);
        } else {
            redisTemplate.opsForValue().set(String.valueOf(type) + "_" + String.valueOf(typeId)+"_counter", count - 1);
        }
    }
    /**
     * 获取点赞数
     *
     * @param type
     * @param typeId
     * @return
     */
    public Integer getIncrement(Integer type, Integer typeId) {
        return (Integer) redisTemplate.opsForValue().get(String.valueOf(type) + "_" + String.valueOf(typeId) + "_counter");
    }

    /**
     * 用set存放每种类型所有被点赞的文章
     *
     * @param type
     * @param typeId
     */
    public void set(Integer type, Integer typeId) {
        redisTemplate.opsForSet().add(String.valueOf(type) + "_set", typeId);
    }

    /**
     * 获取点赞文章的文章id
     *
     * @param type
     * @return
     */
    public Set<Object> getPostId(Integer type) {
        return redisTemplate.opsForSet().members(String.valueOf(type) + "_set");
    }
    public void zset(String type, String typeId, double count) {
        redisTemplate.opsForZSet().add(type, typeId, count);
    }

    /**
     * 用set存放每种类型的每篇文章的点赞用户
     *
     * @param type
     * @param typeId
     * @param uid
     */
    public void set(Integer type,Integer typeId, Integer uid) {
        redisTemplate.opsForSet().add(String.valueOf(type) + "_user_like_" + String.valueOf(typeId), uid);
    }

    /**
     * 获取每个文章点赞的用户id
     *
     * @param type
     * @param typeId
     * @return
     */
    public Set<Object> getUserIdByPost(Integer type, Integer typeId) {
        return redisTemplate.opsForSet().members(String.valueOf(type) + "_user_like_" + String.valueOf(typeId));
    }
}
