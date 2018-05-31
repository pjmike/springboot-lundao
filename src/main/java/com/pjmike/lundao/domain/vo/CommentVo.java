package com.pjmike.lundao.domain.vo;

import lombok.Data;

/**
 * @author pjmike
 * @create 2018-05-31 14:58
 */
@Data
public class CommentVo {
    private Integer id;

    /**
     * 发表人id
     */
    private Integer fromUid;

    /**
     * 发表人姓名
     */
    private String fromUname;

    /**
     * 发表人头像
     */
    private String fromUavatar;

    /**
     * 目标人id
     */
    private Integer toUid;

    /**
     * 目标人姓名
     */
    private String toUname;

    /**
     * 目标人头像
     */
    private String toUavatar;

    /**
     * 论点id
     */
    private Integer thesisId;

    /**
     * 点赞数
     */
    private Integer favour;

    /**
     * 内容
     */
    private String content;

    /**
     * 目标id
     */
    private Integer targetId;

    /**
     * 左滑
     */
    private boolean left = false;
    /**
     * 右滑
     */
    private boolean right = false;

}
