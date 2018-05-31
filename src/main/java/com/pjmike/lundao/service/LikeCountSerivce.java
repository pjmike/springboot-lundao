package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.Likecount;

import java.util.List;

/**
 * 点赞数操作
 *
 * @author pjmike
 * @create 2018-05-31 11:42
 */
public interface LikeCountSerivce extends IService<Likecount> {
    List<Likecount> getLikeCountsByType(Integer type);
}
