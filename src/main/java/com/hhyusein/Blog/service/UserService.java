package com.hhyusein.Blog.service;

import com.hhyusein.Blog.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    User save(User user);
    User update(User user, Long id);
    void delete(Long id);
}
