package com.pjmike.lundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.pjmike.lundao.dao.DebateMapper;
import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;
import com.pjmike.lundao.service.DebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-22 11:54
 */
@Service
public class DebateServiceImpl implements DebateService {
    @Autowired
    private DebateMapper debateMapper;
    @Override
    public Debate save(Debate model) {
        debateMapper.insert(model);
        return model;
    }

    @Override
    public Debate findById(Integer id) {
        return debateMapper.selectById(id);
    }

    @Override
    public List<Debate> findAll() {
        return debateMapper.selectAll();
    }

    @Override
    public List<Debate> findAllByPageAndSize(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Debate.class);
        Example.Criteria criteria = example.createCriteria();
//        example.orderBy("createTime").desc();
        return debateMapper.selectByExample(example);
    }

    @Override
    public DebateVo findDebateWithThesisById(Integer debateId) {
        return debateMapper.selectByIdWithThesis(debateId);
    }
}
