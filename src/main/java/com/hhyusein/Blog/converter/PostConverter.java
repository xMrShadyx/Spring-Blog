package com.hhyusein.Blog.converter;

import com.hhyusein.Blog.dto.PostDto;
import com.hhyusein.Blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostDto toPostDto(Post post) {
        return PostDto.builder()
                .post_id(post.getPost_id())
                .postTitle(post.getPostTitle())
                .user_id(post.getUser_id())
                .topic_id(post.getTopic_id())
                .message(post.getMessage())
                .build();
    }

    public Post toPost(PostDto postDto) {
        return Post.builder()
                .post_id(postDto.getPost_id())
                .postTitle(postDto.getPostTitle())
                .user_id(postDto.getUser_id())
                .topic_id(postDto.getTopic_id())
                .message(postDto.getMessage())
                .build();
    }
}
