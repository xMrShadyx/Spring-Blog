package com.hhyusein.Blog.dto;

import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class postDto {

    private Long post_id;

    private String postTitle;

    private PostTopic topic_id;

    private User user_id;

    private String message;
}
