package com.github.kardzhaliyski.blogwebapp.controllers;

import com.github.kardzhaliyski.blogwebapp.mappers.CommentMapper;
import com.github.kardzhaliyski.blogwebapp.mappers.PostMapper;
import com.github.kardzhaliyski.blogwebapp.models.Comment;
import com.github.kardzhaliyski.blogwebapp.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

//@RestController
@Component
public class PostsControllerImpl implements PostsController {

    private PostMapper postMapper;
    private CommentMapper commentMapper;

    public PostsControllerImpl(PostMapper postMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public Post[] getPosts() {
        return postMapper.getAllPosts();
    }

    @Override
    public Post getPost(int postId) {
        Post post = postMapper.getPostById(postId);
        if (post == null) {
            throw new ResponseStatusException(NOT_FOUND, "Post not found");
        }

        return post;
    }

    @Override
    public Comment[] getCommentsForPost(int postId) {
        return commentMapper.getAllCommentsForPost(postId);
    }

    @Override
    public void deletePost(int postId) {
        postMapper.deleteById(postId);
    }

    @Override
    public ResponseEntity addPost(Post post) {
        if (post.title == null || post.body == null || post.userId == 0) {
            return new ResponseEntity<>("Invalid data", BAD_REQUEST);
//            throw new ResponseStatusException(BAD_REQUEST, "Invalid data!");
        }

        postMapper.addPost(post);
        return new ResponseEntity(post, CREATED);
    }

    @Override
    public Post updatePost(int postId, Post post) {
        if (!postMapper.contains(postId)) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid userId");
        }

        post.id = postId;
        postMapper.updatePost(post);
        return post;
    }
}
