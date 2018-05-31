package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.utils.MyMapper;

import java.util.List;

public interface ThesisMapper extends MyMapper<Thesis> {
    List<Thesis> findAllByDebateId(Integer id);
}