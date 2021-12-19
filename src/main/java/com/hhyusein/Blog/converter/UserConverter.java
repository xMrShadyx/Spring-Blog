package com.hhyusein.Blog.converter;

import com.hhyusein.Blog.dto.UserDto;
import com.hhyusein.Blog.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .user_id(user.getUser_id())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .user_id(userDto.getUser_id())
                .userName(userDto.getUserName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }
}
