package com.hhyusein.Blog.service.Impl;

import com.hhyusein.Blog.exception.DuplicateRecordException;
import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.repository.PostRepository;
import com.hhyusein.Blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID: %d not found", id)));
    }

    @Override
    public Post save(Post post) {
        try {
            return postRepository.save(post);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateRecordException(String.format("Post with ID: %d already exists.", post.getPost_id()));
        }
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postRepository.findAll());
    }

    @Override
    public Post update(Post post, Long id) {
        Post foundPost = findById(id);
        Post updatedPost = Post.builder()
                .post_id(foundPost.getPost_id())
                .user_id(foundPost.getUser_id())
                .postTitle(post.getPostTitle())
                .createDate(post.getCreateDate())
                .message(post.getMessage())
                .topic_id(post.getTopic_id())
                .build();
        return save(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
