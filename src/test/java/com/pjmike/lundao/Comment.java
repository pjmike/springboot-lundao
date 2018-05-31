package com.pjmike.lundao;

import lombok.Data;

/**
 * @author pjmike
 * @create 2018-05-29 15:49
 */
@Data
public class Comment {
    public Integer id;
    public Integer fromUid;
    public String fromUName;
    public String toUName;
    public String toUid;
    public String articleId;
    public String content;
    public Comment() {

    }

    public Comment(String fromUName, String toUName, String content) {
        this.fromUName = fromUName;
        this.toUName = toUName;
        this.content = content;
    }

    public Comment(Integer id, String fromUName, String toUName, String content) {
        this.id = id;
        this.fromUName = fromUName;
        this.toUName = toUName;
        this.content = content;
    }
}
