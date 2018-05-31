package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.Favour;
import com.pjmike.lundao.utils.MyMapper;

public interface LikeMapper extends MyMapper<Favour> {
    int updateLike(Favour favour);

    Favour selectOneFavour(Favour favour);
}