package com.pjmike.lundao.domain;

import javax.persistence.*;

public class Likecount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 点赞类型id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 点赞类型id:1为辩题点赞，2为论点点赞，3为评论回复点赞
     */
    private Integer type;

    /**
     * 点赞数
     */
    private Integer count;

    public Likecount(Integer typeId, Integer type, Integer count) {
        this.typeId = typeId;
        this.type = type;
        this.count = count;
    }

    public Likecount(Integer typeId, Integer type) {
        this.typeId = typeId;
        this.type = type;
    }

    public Likecount() {
    }

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
     * 获取点赞数
     *
     * @return count - 点赞数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置点赞数
     *
     * @param count 点赞数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Likecount{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", type=" + type +
                ", count=" + count +
                '}';
    }
}