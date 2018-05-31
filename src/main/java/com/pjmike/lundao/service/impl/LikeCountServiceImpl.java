package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.LikecountMapper;
import com.pjmike.lundao.domain.Likecount;
import com.pjmike.lundao.service.LikeCountSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-31 11:46
 */
@Service
public class LikeCountServiceImpl implements LikeCountSerivce {
    @Autowired
    private LikecountMapper likecountMapper;

    @Override
    public List<Likecount> getLikeCountsByType(Integer type) {
        Example example = new Example(Likecount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", type);
        return likecountMapper.selectByExample(example);
    }
}
