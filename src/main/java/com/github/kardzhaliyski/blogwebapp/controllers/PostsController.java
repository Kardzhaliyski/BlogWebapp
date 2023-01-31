package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.models.Comment;
import com.github.kardzhaliyski.blogwebapp.models.Post;
import com.github.kardzhaliyski.blogwebapp.models.UserRole;
import com.github.kardzhaliyski.blogwebapp.security.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public interface PostsController {
    @GetMapping(value = {"/", ""})
    @Role(UserRole.USER)
    public Post[] getPosts();

    @GetMapping("/{postId}")
    @Role(UserRole.USER)
    public Post getPost(@PathVariable int postId);

    @GetMapping("/{postId}/comments")
    @Role(UserRole.USER)
    public Comment[] getCommentsForPost(@PathVariable int postId);

    @DeleteMapping("/{postId}")
    @Role(UserRole.USER)
    public void deletePost(int postId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"/", ""})
    @Role(UserRole.USER)
    public ResponseEntity addPost(@RequestBody Post post);

    @PutMapping(value = {"/{postId}"})
    @Role(UserRole.USER)
    public Post updatePost(@PathVariable int postId, @RequestBody Post post);
}
