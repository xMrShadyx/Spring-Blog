package com.hhyusein.Blog.service.Impl;

import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.repository.PostTopicRepository;
import com.hhyusein.Blog.service.PostTopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostTopicServiceImpl implements PostTopicService {

    private final PostTopicRepository postTopicRepository;

    @Override
    public PostTopic findById(Long id) {
        return postTopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Topic with %d id not found", id)));
    }

    @Override
    public List<PostTopic> findAll() {
        return postTopicRepository.findAll();
    }

    @Override
    public PostTopic save(PostTopic postTopic) {
        return postTopicRepository.save(postTopic);
    }

    @Override
    public PostTopic update(PostTopic postTopic, Long id) {
        PostTopic foundTopic = findById(id);
        PostTopic updatedTopic = PostTopic.builder()
                .topic_id(foundTopic.getTopic_id())
                .topicName(postTopic.getTopicName())
                .build();
        return save(updatedTopic);
    }

    @Override
    public void delete(Long id) {
        postTopicRepository.deleteById(id);
    }
}
