package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.converter.PostConverter;
import com.hhyusein.Blog.dto.PostDto;
import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostConverter postConverter;

    @PostMapping
    public ResponseEntity<PostDto> save(@RequestBody PostDto postDto) {
        Post newPost = postConverter.toPost(postDto);
        Post savedPost = postService.save(newPost);
        PostDto postDtoResponse = postConverter.toPostDto(savedPost);
        return ResponseEntity.ok(postDtoResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        Post foundPost = postService.findById(id);
        PostDto postDto = postConverter.toPostDto(foundPost);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDto> update(@RequestBody PostDto postDto,
                                          @PathVariable Long id) {
        Post foundPost = postConverter.toPost(postDto);
        Post savedPost = postService.update(foundPost, id);
        PostDto postDtoResponse = postConverter.toPostDto(savedPost);
        return ResponseEntity.ok(postDtoResponse);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.findById(id);
    }


}
