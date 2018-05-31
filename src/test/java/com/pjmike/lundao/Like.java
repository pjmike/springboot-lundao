package com.pjmike.lundao;

import lombok.Data;

/**
 * @author pjmike
 * @create 2018-05-29 15:52
 */
public class Like {
    private Integer id;
    //赞数
    private Integer count;

    public Like(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public Like() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
