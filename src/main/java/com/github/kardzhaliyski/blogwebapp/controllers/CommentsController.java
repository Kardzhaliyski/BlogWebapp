package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.dao.Dao;
import com.github.kardzhaliyski.blogwebapp.model.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    Dao dao;

    public CommentsController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping({"/", ""})
    public Comment[] getComments() {
        return dao.getAllComments();
    }

    @GetMapping(path = {"/", ""}, params = {"postId"})
    public Comment[] getComment(@RequestParam("postId") int postId) {
        return dao.getAllCommentsForPost(postId);
    }


}
