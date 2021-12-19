package com.hhyusein.Blog.converter;

import com.hhyusein.Blog.dto.PostTopicDto;
import com.hhyusein.Blog.model.PostTopic;
import org.springframework.stereotype.Component;

@Component
public class PostTopicConverter {

    public PostTopicDto toPostTopicDto(PostTopic postTopic) {
        return PostTopicDto.builder()
                .topic_id(postTopic.getTopic_id())
                .topicName(postTopic.getTopicName())
                .build();
    }

    public PostTopic toPostTopic(PostTopicDto postTopicDto) {
        return PostTopic.builder()
                .topic_id(postTopicDto.getTopic_id())
                .topicName(postTopicDto.getTopicName())
                .build();
    }
}
