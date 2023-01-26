package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.models.Comment;
import com.github.kardzhaliyski.blogwebapp.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public interface PostsController {
    @GetMapping(value = {"/", ""})
    public Post[] getPosts();

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId);

    @GetMapping("/{postId}/comments")
    public Comment[] getCommentsForPost(@PathVariable int postId);

    @DeleteMapping("/{postId}")
    public void deletePost(int postId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"/", ""})
    public Post addPost(@RequestBody Post post);

    @PutMapping(value = {"/{postId}"})
    public Post updatePost(@PathVariable int postId, @RequestBody Post post);
}
