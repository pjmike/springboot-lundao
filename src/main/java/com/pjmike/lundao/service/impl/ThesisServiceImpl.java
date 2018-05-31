package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.ThesisMapper;
import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.service.ThesisSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-23 16:28
 */
@Service
public class ThesisServiceImpl implements ThesisSevice {
    @Autowired
    private ThesisMapper thesisMapper;
    @Override
    public Thesis save(Thesis model) {
        thesisMapper.insert(model);
        return model;
    }

    @Override
    public Thesis findById(Integer id) {
        return null;
    }

    @Override
    public List<Thesis> findAllByDebateId(Integer id) {
        return thesisMapper.findAllByDebateId(id);
    }
}
