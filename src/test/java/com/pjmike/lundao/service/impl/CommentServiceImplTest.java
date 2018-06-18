package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.domain.Comment;
import com.pjmike.lundao.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Test
    public void getAllCommentsByThesisId() {
        List<Comment> comments = commentService.getAllCommentsByThesisId(1);
        assertNotNull(comments);
        System.out.println(comments.get(0));
    }

    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setTargetId(1);
        comment.setFromUid(5);
        comment.setFromUavatar("http://osvtz719h.bkt.clouddn.com/lADPBbCc1SjLjH_NAwDNAwA_768_768.jpg");
        comment.setFromUname("pjmike2333");
        comment.setThesisId(1);
        comment.setContent("hello");
        comment.setToUid(1);
        comment.setToUavatar("http://osvtz719h.bkt.clouddn.com/lADPBbCc1SjLjH_NAwDNAwA_768_768.jpg");
        comment.setToUname("pjmike");
        comment = commentService.save(comment);
        System.out.println(comment);
    }

}