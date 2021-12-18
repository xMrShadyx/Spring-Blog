package com.hhyusein.Blog.service.Impl;

import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.repository.UserRepository;
import com.hhyusein.Blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID: %d not found", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        User foundUser = findById(id);
        User updatedUser = User.builder()
                .user_id(foundUser.getUser_id())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        return save(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
