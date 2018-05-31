package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-22 11:53
 */
public interface DebateService extends IService<Debate> {
    List<Debate> findAllByPageAndSize(Integer page, Integer size);

    DebateVo findDebateWithThesisById(Integer debateId);

}
