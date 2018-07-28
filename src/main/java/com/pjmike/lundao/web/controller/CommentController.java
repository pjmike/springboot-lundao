package com.pjmike.lundao.web.controller;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.vo.CommentVo;
import com.pjmike.lundao.service.CommentService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-31 16:45
 */
@RestController
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加评论回复
     *
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public Result insertComments(@RequestBody Comment comment) {
        comment = commentService.save(comment);
        return ResultUtils.success(comment);
   }

    /**
     *
     *
     * @param commentVo
     * @return
     */
    @PostMapping("/comments/list")
    public Result getFinalComments(@RequestBody CommentVo commentVo) {
        List<Comment> comments = commentService.getFinalComments(commentVo);
        return ResultUtils.success(comments);
    }

    /**
     * 返回某一辩题的评论列表
     *
     * @param thesisId
     * @return
     */
    @GetMapping("/comments/thesisId/{thesisId}")
    public Result get(@PathVariable("thesisId")Integer thesisId) {
        List<Comment> comments = commentService.getCommentForThesis(thesisId);
        return ResultUtils.success(comments);
    }
}

