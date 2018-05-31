package com.pjmike.lundao.domain;

import javax.persistence.*;

public class Comment {
    @Id
    private Integer id;

    /**
     * 发表人id
     */
    @Column(name = "from_uid")
    private Integer fromUid;

    /**
     * 发表人姓名
     */
    @Column(name = "from_uname")
    private String fromUname;

    /**
     * 发表人头像
     */
    @Column(name = "from_uavatar")
    private String fromUavatar;

    /**
     * 目标人id
     */
    @Column(name = "to_uid")
    private Integer toUid;

    /**
     * 目标人姓名
     */
    @Column(name = "to_uname")
    private String toUname;

    /**
     * 目标人头像
     */
    @Column(name = "to_uavatar")
    private String toUavatar;

    /**
     * 论点id
     */
    @Column(name = "thesis_id")
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
    @Column(name = "target_id")
    private Integer targetId;

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
     * 获取发表人id
     *
     * @return from_uid - 发表人id
     */
    public Integer getFromUid() {
        return fromUid;
    }

    /**
     * 设置发表人id
     *
     * @param fromUid 发表人id
     */
    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    /**
     * 获取发表人姓名
     *
     * @return from_uname - 发表人姓名
     */
    public String getFromUname() {
        return fromUname;
    }

    /**
     * 设置发表人姓名
     *
     * @param fromUname 发表人姓名
     */
    public void setFromUname(String fromUname) {
        this.fromUname = fromUname;
    }

    /**
     * 获取发表人头像
     *
     * @return from_uavatar - 发表人头像
     */
    public String getFromUavatar() {
        return fromUavatar;
    }

    /**
     * 设置发表人头像
     *
     * @param fromUavatar 发表人头像
     */
    public void setFromUavatar(String fromUavatar) {
        this.fromUavatar = fromUavatar;
    }

    /**
     * 获取目标人id
     *
     * @return to_uid - 目标人id
     */
    public Integer getToUid() {
        return toUid;
    }

    /**
     * 设置目标人id
     *
     * @param toUid 目标人id
     */
    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    /**
     * 获取目标人姓名
     *
     * @return to_uname - 目标人姓名
     */
    public String getToUname() {
        return toUname;
    }

    /**
     * 设置目标人姓名
     *
     * @param toUname 目标人姓名
     */
    public void setToUname(String toUname) {
        this.toUname = toUname;
    }

    /**
     * 获取目标人头像
     *
     * @return to_uavatar - 目标人头像
     */
    public String getToUavatar() {
        return toUavatar;
    }

    /**
     * 设置目标人头像
     *
     * @param toUavatar 目标人头像
     */
    public void setToUavatar(String toUavatar) {
        this.toUavatar = toUavatar;
    }

    /**
     * 获取论点id
     *
     * @return thesis_id - 论点id
     */
    public Integer getThesisId() {
        return thesisId;
    }

    /**
     * 设置论点id
     *
     * @param thesisId 论点id
     */
    public void setThesisId(Integer thesisId) {
        this.thesisId = thesisId;
    }

    /**
     * 获取点赞数
     *
     * @return favour - 点赞数
     */
    public Integer getFavour() {
        return favour;
    }

    /**
     * 设置点赞数
     *
     * @param favour 点赞数
     */
    public void setFavour(Integer favour) {
        this.favour = favour;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取目标id
     *
     * @return target_id - 目标id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * 设置目标id
     *
     * @param targetId 目标id
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", fromUid=" + fromUid +
                ", fromUname='" + fromUname + '\'' +
                ", fromUavatar='" + fromUavatar + '\'' +
                ", toUid=" + toUid +
                ", toUname='" + toUname + '\'' +
                ", toUavatar='" + toUavatar + '\'' +
                ", thesisId=" + thesisId +
                ", favour=" + favour +
                ", content='" + content + '\'' +
                ", targetId=" + targetId +
                '}';
    }
}