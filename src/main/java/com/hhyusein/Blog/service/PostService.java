package com.hhyusein.Blog.service;

import com.hhyusein.Blog.model.Post;

import java.util.List;

public interface PostService {

    Post findById(Long id);

    Post save(Post post);

    List<List<Post>> findAll();

    Post update(Post post, Long id);

    void deletePost(Long id);
}
