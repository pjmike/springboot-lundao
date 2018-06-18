package com.pjmike.lundao.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

public class Debate {
    /**
     * 辩题主键
     */
    @Id
    @Column(name = "debate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer debateId;

    /**
     * 题目
     */
    private String title;

    /**
     * 详情
     */
    private String content;
    /**
     * 图片
     */
    private String image;

    /**
     * 创建时间
     */
    @Column(name = "create_time")

    private Date createTime;

    /**
     * 获取辩题主键
     *
     * @return debate_id - 辩题主键
     */
    public Integer getDebateId() {
        return debateId;
    }

    /**
     * 设置辩题主键
     *
     * @param debateId 辩题主键
     */
    public void setDebateId(Integer debateId) {
        this.debateId = debateId;
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
     * 获取详情
     *
     * @return content - 详情
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置详情
     *
     * @param content 详情
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Debate{" +
                "debateId=" + debateId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}