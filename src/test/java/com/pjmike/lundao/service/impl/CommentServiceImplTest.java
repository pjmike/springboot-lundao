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

}