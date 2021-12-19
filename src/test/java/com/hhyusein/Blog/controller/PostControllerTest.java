package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.converter.PostConverter;
import com.hhyusein.Blog.dto.PostDto;
import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.service.PostService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = PostController.class)
public class PostControllerTest extends BaseControllerTest{

    @MockBean
    private PostService postService;

    @MockBean
    private PostConverter postConverter;


    @Test
    public void save() throws Exception {

        PostDto postDto = PostDto.builder()
                .post_id(1L)
                .postTitle("Test Title")
                .message("Post Test Message")
                .topic_id(new PostTopic())
                .user_id(new User())
                .build();

        String reqJson = objectMapper.writeValueAsString(postDto);

        when(postConverter.toPost(any(PostDto.class))).thenReturn(Post.builder().build());
        when(postService.save(any(Post.class))).thenReturn(Post.builder().build());
        when(postConverter.toPostDto(any(Post.class))).thenReturn(PostDto.builder()
                .post_id(1L)
                .postTitle("Test Title")
                .message("Post Test Message")
                .topic_id(new PostTopic())
                .user_id(new User())
                .build());

        mockMvc.perform(post("/posts")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.post_id", is(1)))
                .andExpect(jsonPath("$.postTitle", is("Test Title")))
                .andExpect(jsonPath("$.message", is("Post Test Message")));

    }

    @Test
    public void findById() throws Exception {
        when(postService.findById(any(Long.class))).thenReturn(Post.builder().build());
        when(postConverter.toPostDto(any(Post.class))).thenReturn(PostDto.builder()
                .post_id(1L)
                .postTitle("Test Title")
                .message("Post Test Message")
                .topic_id(new PostTopic())
                .user_id(new User())
                .build());

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.post_id", is(1)))
                .andExpect(jsonPath("$.postTitle", is("Test Title")))
                .andExpect(jsonPath("$.message", is("Post Test Message")));
    }

    @Test
    public void postDelete() throws Exception {
        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePost() throws Exception {
        PostDto postDto = PostDto.builder()
                .post_id(1L)
                .postTitle("Test Title")
                .message("Post Test Message")
                .topic_id(new PostTopic())
                .user_id(new User())
                .build();

        String reqJson = objectMapper.writeValueAsString(postDto);
        when(postConverter.toPostDto(any())).thenReturn(postDto);

        mockMvc.perform(put("/posts/1")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.post_id", is(1)))
                .andExpect(jsonPath("$.postTitle", is("Test Title")))
                .andExpect(jsonPath("$.message", is("Post Test Message")));
    }
}
