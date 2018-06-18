package com.pjmike.lundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.pjmike.lundao.dao.ThesisMapper;
import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.bo.Thesis;
import com.pjmike.lundao.service.ThesisSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<Thesis> findThesisByPageAndSize(Integer page, Integer size,Integer debateId) {
        PageHelper.startPage(page, size);
        Example example = new Example(Thesis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("debateId", debateId);
        example.orderBy("createTime").desc();
        return thesisMapper.selectByExample(example);
    }

    @Override
    public void update(Thesis model) {
        thesisMapper.updateByPrimaryKey(model);
    }

    @Override
    public List<Thesis> findByCondition(Condition condition) {
        return thesisMapper.selectByExample(condition);
    }
}
