package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.mappers.CommentMapper;
import com.github.kardzhaliyski.blogwebapp.models.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {


    private CommentMapper commentMapper;

    public CommentsController(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }

    @GetMapping({"/", ""})
    public Comment[] getComments() {
        return commentMapper.getAllComments();
    }

    @GetMapping(path = {"/", ""}, params = {"postId"})
    public Comment[] getComment(@RequestParam("postId") int postId) {
        return commentMapper.getAllCommentsForPost(postId);
    }


}
