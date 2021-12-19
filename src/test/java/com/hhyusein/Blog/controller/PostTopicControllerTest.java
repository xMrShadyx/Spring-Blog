package com.hhyusein.Blog.controller;


import com.hhyusein.Blog.converter.PostTopicConverter;
import com.hhyusein.Blog.dto.PostTopicDto;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.service.PostTopicService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = PostTopicController.class)
public class PostTopicControllerTest extends BaseControllerTest{

    @MockBean
    private PostTopicService postTopicService;

    @MockBean
    private PostTopicConverter postTopicConverter;

    @Test
    public void save() throws Exception {
        PostTopicDto topicDto = PostTopicDto.builder()
                .topic_id(1L)
                .topicName("testTopic")
                .build();

        String reqJson = objectMapper.writeValueAsString(topicDto);

        when(postTopicConverter.toPostTopic(any(PostTopicDto.class))).thenReturn(PostTopic.builder().build());
        when(postTopicService.save(any(PostTopic.class))).thenReturn(PostTopic.builder().build());
        when(postTopicConverter.toPostTopicDto(any(PostTopic.class))).thenReturn(PostTopicDto.builder().topic_id(1L).topicName("testTopic").build());

        mockMvc.perform(post("/topics")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.topic_id", is(1)))
                .andExpect(jsonPath("$.topicName", is("testTopic")));
    }

    @Test
    public void findById() throws Exception {
        when(postTopicService.findById(any(Long.class))).thenReturn(PostTopic.builder().build());
        when(postTopicConverter.toPostTopicDto(any(PostTopic.class))).thenReturn(PostTopicDto.builder().topic_id(1L).topicName("testTopic").build());

        mockMvc.perform(get("/topics/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.topic_id", is(1)))
                .andExpect(jsonPath("$.topicName", is("testTopic")));
    }

    @Test
    public void deleteTopic() throws Exception {
        mockMvc.perform(delete("/topics/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTopic() throws Exception {
        PostTopicDto topicDto = PostTopicDto.builder()
                .topic_id(1L)
                .topicName("testTopic")
                .build();

        String reqJson = objectMapper.writeValueAsString(topicDto);

        when(postTopicConverter.toPostTopicDto(any())).thenReturn(topicDto);

        mockMvc.perform(put("/topics/1")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.topic_id", is(1)))
                .andExpect(jsonPath("$.topicName", is("testTopic")));
    }
}
