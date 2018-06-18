package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.bo.Thesis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThesisSeviceTest {
    @Autowired
    private ThesisSevice thesisSevice;
    @Test
    public void findThesisByPageAndSize() {
        List<Thesis> list = thesisSevice.findThesisByPageAndSize(1, 10,1);
        System.out.println("=====");
        System.out.println(list);
    }

    @Test
    public void get() {
        Condition condition = new Condition(Thesis.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("uid", 1);
        List<Thesis> thesisList = thesisSevice.findByCondition(condition);
        System.out.println(thesisList);
    }
}