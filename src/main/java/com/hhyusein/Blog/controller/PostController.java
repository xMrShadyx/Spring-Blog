package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        Post savePost = Post.builder()
                .createDate(post.getCreateDate())
                .postTitle(post.getPostTitle())
                .user_id(post.getUser_id())
                .topic_id(post.getTopic_id())
                .message(post.getMessage())
                .build();
        return ResponseEntity.ok(postService.save(savePost));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }


}
