package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;
import com.pjmike.lundao.utils.MyMapper;

public interface DebateMapper extends MyMapper<Debate> {
    Debate selectById(Integer id);

    DebateVo selectByIdWithThesis(Integer id);
}