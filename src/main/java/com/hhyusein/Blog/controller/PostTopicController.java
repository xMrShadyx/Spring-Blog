package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.service.PostTopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/topics")
@AllArgsConstructor
public class PostTopicController {

    private final PostTopicService postTopicService;

    @PostMapping
    public ResponseEntity<PostTopic> save(@RequestBody PostTopic postTopic) {
        return ResponseEntity.ok(postTopicService.save(postTopic));
    }
}
