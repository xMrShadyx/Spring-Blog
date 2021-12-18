package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
@AllArgsConstructor
public class PostController {

    private final PostService post;
}
