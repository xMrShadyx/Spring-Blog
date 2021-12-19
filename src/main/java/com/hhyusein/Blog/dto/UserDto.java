package com.hhyusein.Blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {

    private Long user_id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;
}
