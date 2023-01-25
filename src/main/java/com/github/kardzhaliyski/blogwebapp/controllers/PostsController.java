package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.dao.Dao;
import com.github.kardzhaliyski.blogwebapp.model.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return post;
    }

    @GetMapping("/{postId}/comments")
    public void getCommentsForPost(@PathVariable int postId, HttpServletRequest request) {
//        request.getRequestDispatcher("/comments?postId=" + postId).forward();
        //todo
    }

    @DeleteMapping("/{postId}")
    public void deletePost(int postId) {
        dao.deletePostById(postId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"/", ""})
    public Post addPost(@RequestBody Post post) {
        if (post.getTitle() == null || post.getBody() == null || post.getUserId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data!");
        }

        dao.addPost(post);
        return post;
    }

    @PutMapping({"/{postId}"})
    public Post updatePost(@PathVariable int postId, @RequestBody Post post) {
        if(!dao.containsPost(postId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
        }

        post.setId(postId);
        dao.updatePost(post);
        return post;
    }

}
