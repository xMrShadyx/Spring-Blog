package com.hhyusein.Blog.service.impl;

import com.hhyusein.Blog.exception.DuplicateRecordException;
import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.Post;
import com.hhyusein.Blog.repository.PostRepository;
import com.hhyusein.Blog.service.Impl.PostServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Test
    public void verifySave() {
        Post post = Post.builder().build();
        when(postRepository.save(any(Post.class))).thenReturn(post);
        postServiceImpl.save(post);

        verify(postRepository, times(1)).save(post);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {
        when(postRepository.save(any(Post.class)))
                .thenThrow(DataIntegrityViolationException.class);

        postServiceImpl.save(Post.builder().build());
    }

    @Test
    public void verifyFindById() {
        when(postRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(Post.builder().build()));

        postServiceImpl.findById(1L);
        verify(postRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByException() {
        when(postRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        postServiceImpl.findById(1L);
    }

    @Test
    public void verifyDelete() {
        doNothing().when(postRepository).deleteById(any(Long.class));
        postServiceImpl.deletePost(1L);
        verify(postRepository, times(1)).deleteById(1L);
    }
}
