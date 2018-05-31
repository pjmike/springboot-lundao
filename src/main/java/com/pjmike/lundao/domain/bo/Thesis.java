package com.pjmike.lundao.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class Thesis {
    /**
     * 主键
     */
    @Id
    @Column(name = "thesis_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thesisId;

    /**
     * 辩题id
     */
    @Column(name = "debate_id")
    private Integer debateId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 题目
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return thesis_id - 主键
     */
    public Integer getThesisId() {
        return thesisId;
    }

    /**
     * 设置主键
     *
     * @param thesisId 主键
     */
    public void setThesisId(Integer thesisId) {
        this.thesisId = thesisId;
    }

    /**
     * 获取辩题id
     *
     * @return debate_id - 辩题id
     */
    public Integer getDebateId() {
        return debateId;
    }

    /**
     * 设置辩题id
     *
     * @param debateId 辩题id
     */
    public void setDebateId(Integer debateId) {
        this.debateId = debateId;
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
     * 获取题目
     *
     * @return title - 题目
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置题目
     *
     * @param title 题目
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "thesisId=" + thesisId +
                ", debateId=" + debateId +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}