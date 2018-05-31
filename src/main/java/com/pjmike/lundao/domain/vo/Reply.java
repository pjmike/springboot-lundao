package com.pjmike.lundao.domain.vo;

import lombok.Data;

/**
 * @author pjmike
 * @create 2018-05-26 10:09
 */
@Data
public class Reply {
    public Integer id;
    public Integer from_uid;
    public Integer to_uid;
    public String content;

    public Reply(Integer id, Integer from_uid, Integer to_uid, String content) {
        this.id = id;
        this.from_uid = from_uid;
        this.to_uid = to_uid;
        this.content = content;
    }
}
