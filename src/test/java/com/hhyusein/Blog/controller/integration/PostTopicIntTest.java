package com.hhyusein.Blog.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhyusein.Blog.dto.PostTopicDto;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.repository.PostTopicRepository;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostTopicIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostTopicRepository postTopicRepository;

    @Test
    public void savePostTopic() throws Exception {
        PostTopicDto postTopicDto = PostTopicDto.builder().topicName("Test Name").build();
        String jsonRequest = objectMapper.writeValueAsString(postTopicDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                    .when()
                .post("http://localhost:8080/topics")
                .then()
                .statusCode(200)
                .body("topic_id", equalTo(1))
                .body("topicName", equalTo("Test Name"));
    }

    @Test
    public void updateTopic() throws Exception {
        postTopicRepository.save(PostTopic.builder().topicName("Test Topic").build());
        PostTopicDto topicDto = PostTopicDto.builder().topic_id(1L).topicName("Test Topic 2").build();
        String jsonRequest = objectMapper.writeValueAsString(topicDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .put("http://localhost:8080/topics/1")
                .then()
                .statusCode(200)
                .body("topic_id", equalTo(1))
                .body("topicName", equalTo("Test Topic 2"));
    }
}
