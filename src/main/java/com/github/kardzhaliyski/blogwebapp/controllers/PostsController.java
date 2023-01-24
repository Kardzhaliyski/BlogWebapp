package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.dao.Dao;
import com.github.kardzhaliyski.blogwebapp.model.Post;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    Dao dao;

    public PostsController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping({"/", ""})
    public Post[] getPosts() {
        return dao.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = dao.getPostById(postId);
        if (post == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return post;
    }

    @GetMapping("/{postId}/comments")
    public String getCommentsForPost(@PathVariable int postId) {
        //todo
        return null;
    }


}
