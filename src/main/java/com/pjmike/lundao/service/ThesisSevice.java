package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.bo.Thesis;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-23 16:27
 */
public interface ThesisSevice extends IService<Thesis> {
    List<Thesis> findAllByDebateId(Integer id);
}
