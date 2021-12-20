package com.hhyusein.Blog.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhyusein.Blog.dto.PostDto;
import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.repository.PostRepository;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void savePost() throws Exception {

        PostDto postDto = PostDto.builder()
                .postTitle("Test Title")
                .message("Test Message")
                .user_id(any(User.class))
                .topic_id(any(PostTopic.class))
                .build();
        String jsonRequest = objectMapper.writeValueAsString(postDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .when()
                .post("http://localhost:8080/posts")
                .then()
                .statusCode(200)
                .body("postTitle", equalTo("Test Title"))
                .body("message", equalTo("Test Message"));
    }

    @Test
    public void updatePost() throws Exception {
        postRepository.save(Post.builder()
                .postTitle("Test Title")
                .message("Test Message")
                .user_id(any(User.class))
                .topic_id(any(PostTopic.class))
                .build());

        PostDto postDto = PostDto.builder()
                .post_id(1L)
                .postTitle("Test Title")
                .message("Test Message")
                .user_id(any(User.class))
                .topic_id(any(PostTopic.class))
                .build();

        String jsonRequest = objectMapper.writeValueAsString(postDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .put("http://localhost:8080/posts/1")
                .then()
                .statusCode(200)
                .body("post_id", equalTo(1))
                .body("postTitle", equalTo("Test Title"))
                .body("message", equalTo("Test Message"));

    }
}
