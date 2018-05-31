package com.pjmike.lundao.domain;

import javax.persistence.*;
import java.io.Serializable;

public class Favour implements Serializable {
    private static final long serialVersionUID = -3164921301652237855L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 点赞类型id:1为辩题点赞，2为论点点赞，3为评论和回复点赞
     */
    private Integer type;

    /**
     * 点赞类型id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 赞的状态，0为取消赞，1为有效赞
     */
    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取点赞类型id:1为辩题点赞，2为论点点赞，3为评论点赞，4为回复点赞
     *
     * @return type - 点赞类型id:1为辩题点赞，2为论点点赞，3为评论点赞，4为回复点赞
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置点赞类型id:1为辩题点赞，2为论点点赞，3为评论点赞，4为回复点赞
     *
     * @param type 点赞类型id:1为辩题点赞，2为论点点赞，3为评论点赞，4为回复点赞
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取点赞类型id
     *
     * @return type_id - 点赞类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置点赞类型id
     *
     * @param typeId 点赞类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取赞的状态，0为取消赞，1为有效赞
     *
     * @return status - 赞的状态，0为取消赞，1为有效赞
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置赞的状态，0为取消赞，1为有效赞
     *
     * @param status 赞的状态，0为取消赞，1为有效赞
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}