package com.pjmike.lundao.web.controller;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.domain.vo.CommentVo;
import com.pjmike.lundao.service.CommentService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/comments")
    public Result insertComments(@RequestBody Comment comment) {
        comment = commentService.save(comment);
        return ResultUtils.success(comment);
   }

    @PostMapping("/comments/list")
    public Result getFinalComments(@RequestBody CommentVo commentVo) {
        List<Comment> comments = commentService.getFinalComments(commentVo);
        return ResultUtils.success(comments);
    }
}

