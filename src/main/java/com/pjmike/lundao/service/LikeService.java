package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.Favour;

/**
 * @author pjmike
 * @create 2018-05-26 14:48
 */
public interface LikeService extends IService<Favour> {
    Integer getLikeCount(Integer type,Integer typeId);
}
