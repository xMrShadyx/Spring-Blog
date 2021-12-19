package com.hhyusein.Blog.controller;

import com.hhyusein.Blog.converter.UserConverter;
import com.hhyusein.Blog.dto.UserDto;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        User newUser = userConverter.toUser(userDto);
        User savedUser = userService.save(newUser);
        UserDto userDtoResponse = userConverter.toUserDto(savedUser);
        return ResponseEntity.ok(userDtoResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        User foundUser = userService.findById(id);
        UserDto userDto = userConverter.toUserDto(foundUser);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto,
                                          @PathVariable Long id) {
        User foundUser = userConverter.toUser(userDto);
        User savedUser = userService.update(foundUser, id);
        UserDto userDtoResponse = userConverter.toUserDto(savedUser);
        return ResponseEntity.ok(userDtoResponse);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.findById(id);
    }


}
