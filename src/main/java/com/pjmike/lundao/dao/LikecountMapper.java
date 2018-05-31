package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.Likecount;
import com.pjmike.lundao.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface LikecountMapper extends MyMapper<Likecount> {
    Likecount selectByTypeAndTypeId(@Param("type") Integer type, @Param("typeId") Integer typeId);

    int updateCount(Likecount likecount);
}