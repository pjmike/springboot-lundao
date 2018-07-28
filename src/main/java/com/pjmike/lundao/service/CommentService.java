package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.vo.CommentVo;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-30 9:45
 */
public interface CommentService extends IService<Comment>{
    /**
     * 查询一个文章下的所有评论与回复
     *
     * @param thesisId
     * @return
     */
    List<Comment> getAllCommentsByThesisId(Integer thesisId);

    /**
     * 得到最终的对话列表
     *
     * @return
     */
    List<Comment> getFinalComments(CommentVo commentVo);


    List<Comment> getCommentForThesis(Integer thesisId);
}
