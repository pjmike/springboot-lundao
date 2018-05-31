package com.pjmike.lundao.domain.vo;

import com.alibaba.fastjson.JSON;
import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.bo.Thesis;

import java.util.List;

/**
 * debate传输类
 *
 * @author pjmike
 * @create 2018-05-23 17:09
 */
public class DebateVo extends Debate {
    List<Thesis> thesisList;

    public List<Thesis> getThesisList() {
        return thesisList;
    }

    public void setThesisList(List<Thesis> thesisList) {
        this.thesisList = thesisList;
    }

    @Override
    public String toString() {
        return "DebateVo{" +
                "thesisList=" + thesisList +
                '}';
    }
}
