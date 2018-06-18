package com.pjmike.lundao.domain.vo;

import com.pjmike.lundao.domain.bo.Thesis;

/**
 * @author pjmike
 * @create 2018-06-11 0:04
 */

public class ThesisVo extends Thesis {
    /**
     * 是否点赞,0为点赞，1为不点赞,默认为1，不点赞
     */
    private Integer like = 1;

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "ThesisVo{" +
                "like=" + like +
                '}';
    }
}
