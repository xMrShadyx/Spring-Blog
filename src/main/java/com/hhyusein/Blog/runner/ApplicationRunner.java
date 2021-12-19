package com.hhyusein.Blog.runner;

import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.service.PostService;
import com.hhyusein.Blog.service.PostTopicService;
import com.hhyusein.Blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    // READ FIRST: If you uncomment this file, integration tests won't work!
    // you should increase each id value by 1 in PostTopicIntTest.java

    private final PostService postService;
    private final PostTopicService postTopicService;
    private final UserService userService;


    @Override
    public void run(String... args) throws Exception {
        User newUser = userService.save(User.builder()
                        .userName("xMrShadyx")
                        .firstName("Hyusein")
                        .lastName("Hyusein")
                        .email("daredevil91138@gmail.com")
                .build());

        postTopicService.save(PostTopic.builder()
                .topicName("Coding").build());

        postService.save(Post.builder()
                        .user_id(userService.findById(newUser.getUser_id()))
                .topic_id(postTopicService.findById(1L))
                .postTitle("Post About Programming")
                .createDate(LocalDateTime.now())
                .message("It's always fun when you don't have bugs.").build());
    }
}
