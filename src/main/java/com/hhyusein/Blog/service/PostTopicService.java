package com.hhyusein.Blog.service;

import com.hhyusein.Blog.model.PostTopic;

import java.util.List;

public interface PostTopicService {

    PostTopic findById(Long id);
    List<PostTopic> findAll();
    PostTopic save(PostTopic postTopic);
    PostTopic update(PostTopic postTopic, Long id);
    void delete(Long id);
}
