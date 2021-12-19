package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.converter.UserConverter;
import com.hhyusein.Blog.dto.UserDto;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.service.UserService;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
public class UserControllerTest extends BaseControllerTest{

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

    @Test
    public void save() throws Exception {
        UserDto userDto = UserDto.builder()
                .user_id(1L)
                .email("test@test.com")
                .firstName("firstTestUser")
                .lastName("lastTestUser")
                .userName("testUser")
                .build();

        String reqJson = objectMapper.writeValueAsString(userDto);

        when(userConverter.toUser(any(UserDto.class))).thenReturn(User.builder().build());
        when(userService.save(any(User.class))).thenReturn(User.builder().build());
        when(userConverter.toUserDto(any(User.class))).thenReturn(UserDto.builder()
                .user_id(1L)
                .email("test@test.com")
                .firstName("firstTestUser")
                .lastName("lastTestUser")
                .userName("testUser")
                .build());

        mockMvc.perform(post("/users")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user_id", is(1)))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.firstName", is("firstTestUser")))
                .andExpect(jsonPath("$.lastName", is("lastTestUser")))
                .andExpect(jsonPath("$.userName", is("testUser")));
    }

    @Test
    public void findById() throws Exception {
        when(userService.findById(any(Long.class))).thenReturn(User.builder().build());
        when(userConverter.toUserDto(any(User.class))).thenReturn(UserDto.builder()
                .user_id(1L)
                .email("test@test.com")
                .firstName("firstTestUser")
                .lastName("lastTestUser")
                .userName("testUser")
                .build());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user_id", is(1)))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.firstName", is("firstTestUser")))
                .andExpect(jsonPath("$.lastName", is("lastTestUser")))
                .andExpect(jsonPath("$.userName", is("testUser")));
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .user_id(1L)
                .email("test@test.com")
                .firstName("firstTestUser")
                .lastName("lastTestUser")
                .userName("testUser")
                .build();

        String reqJson = objectMapper.writeValueAsString(userDto);

        when(userConverter.toUserDto(any())).thenReturn(userDto);

        mockMvc.perform(put("/users/1")
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user_id", is(1)))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.firstName", is("firstTestUser")))
                .andExpect(jsonPath("$.lastName", is("lastTestUser")))
                .andExpect(jsonPath("$.userName", is("testUser")));
    }
}
