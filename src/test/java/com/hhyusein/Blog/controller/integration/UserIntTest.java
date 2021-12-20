package com.hhyusein.Blog.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhyusein.Blog.dto.UserDto;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.repository.UserRepository;
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
public class UserIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .email("test@email.com")
                .firstName("firstTest")
                .lastName("lastTest")
                .userName("Test")
                .build();

        String jsonRequest = objectMapper.writeValueAsString(userDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .when()
                .post("http://localhost:8080/users")
                .then()
                .statusCode(200)
                .body("email", equalTo("test@email.com"))
                .body("firstName", equalTo("firstTest"))
                .body("lastName", equalTo("lastTest"))
                .body("userName", equalTo("Test"));
    }

    @Test
    public void updateUser() throws Exception {
        userRepository.save(User.builder().user_id(1L).email("test@email.com")
                .firstName("firstTest")
                .lastName("lastTest")
                .userName("Test")
                .build());

        UserDto userDto = UserDto.builder().user_id(1L).email("test@email.com")
                .firstName("firstTest")
                .lastName("lastTest")
                .userName("Test")
                .build();

        String jsonRequest = objectMapper.writeValueAsString(userDto);

        given()
                .contentType(ContentType.APPLICATION_JSON.toString())
                .body(jsonRequest)
                .put("http://localhost:8080/users/1")
                .then()
                .statusCode(200)
                .body("email", equalTo("test@email.com"))
                .body("firstName", equalTo("firstTest"))
                .body("lastName", equalTo("lastTest"))
                .body("userName", equalTo("Test"));

    }
}
