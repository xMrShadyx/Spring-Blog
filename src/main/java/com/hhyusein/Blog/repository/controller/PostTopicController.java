package com.hhyusein.Blog.repository.controller;

import com.hhyusein.Blog.converter.PostTopicConverter;
import com.hhyusein.Blog.dto.PostTopicDto;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.service.PostTopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics")
@AllArgsConstructor
public class PostTopicController {

    private final PostTopicService postTopicService;
    private final PostTopicConverter postTopicConverter;

    @PostMapping
    public ResponseEntity<PostTopicDto> save(@RequestBody PostTopicDto postTopicDto) {
        PostTopic newPostTopic = postTopicConverter.toPostTopic(postTopicDto);
        PostTopic savedTopic = postTopicService.save(newPostTopic);
        PostTopicDto topicDtoResponse = postTopicConverter.toPostTopicDto(savedTopic);

        return ResponseEntity.ok(topicDtoResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostTopicDto> findById(@PathVariable Long id) {
        PostTopic foundTopic = postTopicService.findById(id);
        PostTopicDto postTopicDto = postTopicConverter.toPostTopicDto(foundTopic);

        return ResponseEntity.ok(postTopicDto);
    }

    @GetMapping
    public ResponseEntity<List<PostTopic>> findAll() {
        return ResponseEntity.ok(postTopicService.findAll());

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostTopicDto> update(@RequestBody PostTopicDto postTopicDto,
                                               @PathVariable Long id) {
        PostTopic foundTopic = postTopicConverter.toPostTopic(postTopicDto);
        PostTopic savedTopic = postTopicService.update(foundTopic, id);
        PostTopicDto postDtoResponse = postTopicConverter.toPostTopicDto(savedTopic);
        return ResponseEntity.ok(postDtoResponse);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTopic(@PathVariable Long id) {
        postTopicService.delete(id);
    }
}
