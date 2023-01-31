package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.models.Comment;
import com.github.kardzhaliyski.blogwebapp.models.UserRole;
import com.github.kardzhaliyski.blogwebapp.security.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public interface CommentsController {

    @GetMapping({"/", ""})
    @Role(UserRole.USER)
    public Comment[] getComments();

    @GetMapping(path = {"/", ""}, params = {"postId"})
    @Role(UserRole.USER)
    public Comment[] getComment(@RequestParam("postId") int postId);
}
