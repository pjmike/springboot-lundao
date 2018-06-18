package com.pjmike.lundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.pjmike.lundao.dao.DebateMapper;
import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;
import com.pjmike.lundao.domain.vo.ThesisVo;
import com.pjmike.lundao.service.DebateService;
import com.pjmike.lundao.utils.enums.TypeEnum;
import com.pjmike.lundao.utils.redis.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.WindowElement;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 * @author pjmike
 * @create 2018-05-22 11:54
 */
@Service
@Slf4j
public class DebateServiceImpl implements DebateService {
    @Autowired
    private DebateMapper debateMapper;
    @Autowired
    private RedisKeyUtils redisKeyUtils;
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
//        criteria.andEqualTo("")
//        example.orderBy("createTime").desc();
        return debateMapper.selectByExample(example);
    }

    @Override
    public DebateVo findWithThesisIdAndWithoutUser(Integer debateId) {
        return debateMapper.selectByIdWithThesis(debateId);
    }

    @Override
    public DebateVo findWithThesisId(Integer userId,Integer debateId)
    {
        DebateVo debateVo = debateMapper.selectByIdWithThesis(debateId);
        List<ThesisVo> thesisVoList = debateVo.getThesisList();
        if (thesisVoList != null) {
            for (ThesisVo thesisVo: thesisVoList) {
                Set<Object> userIds = redisKeyUtils.getUserIdByPost(TypeEnum.THESIS.getType(), thesisVo.getThesisId());
                if (userIds != null) {
                    log.info("论点 {} 有 {} 人点赞",thesisVo.getThesisId(),userIds.size());
                    if (userIds.contains(userId)) {
                        log.info("用户 {} 对 论点{} 点赞", userId, thesisVo.getThesisId());
                        //改变点赞状态
                        thesisVo.setLike(0);
                    }
                }
            }
        }
        return debateVo;
    }
}
