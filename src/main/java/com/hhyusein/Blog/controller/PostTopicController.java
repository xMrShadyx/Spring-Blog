package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.service.PostTopicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/topics")
@AllArgsConstructor
public class PostTopicController {

    private final PostTopicService postTopicService;
}
