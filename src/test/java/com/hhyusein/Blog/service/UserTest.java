package com.hhyusein.Blog.service;

import com.hhyusein.Blog.exception.DuplicateRecordException;
import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.User;
import com.hhyusein.Blog.repository.UserRepository;
import com.hhyusein.Blog.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void verifySave() {
        User user = User.builder().build();
        when(userRepository.save(any(User.class))).thenReturn(user);
        userServiceImpl.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {
        when(userRepository.save(any(User.class)))
                .thenThrow(DataIntegrityViolationException.class);

        userServiceImpl.save(User.builder().build());
    }

    @Test
    public void verifyFindById() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(User.builder().build()));

        userServiceImpl.findById(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByException() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        userServiceImpl.findById(1L);
    }

    @Test
    public void verifyDelete() {
        doNothing().when(userRepository).deleteById(any(Long.class));
        userServiceImpl.delete(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

}
