package com.hhyusein.Blog.service.impl;

import com.hhyusein.Blog.exception.DuplicateRecordException;
import com.hhyusein.Blog.exception.ResourceNotFoundException;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.repository.PostTopicRepository;
import com.hhyusein.Blog.service.Impl.PostTopicServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostTopicTest {

    @Mock
    private PostTopicRepository postTopicRepository;

    @InjectMocks
    private PostTopicServiceImpl postTopicServiceImpl;

    @Test
    public void verifySave() {
        PostTopic postTopic = PostTopic.builder().build();
        when(postTopicRepository.save(any(PostTopic.class))).thenReturn(postTopic);
        postTopicServiceImpl.save(postTopic);

        verify(postTopicRepository, times(1)).save(postTopic);
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifySaveDuplicateException() {
        when(postTopicRepository.save(any(PostTopic.class)))
                .thenThrow(DataIntegrityViolationException.class);

        postTopicServiceImpl.save(PostTopic.builder().build());
    }

    @Test
    public void verifyFindById() {
        when(postTopicRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(PostTopic.builder().build()));

        postTopicServiceImpl.findById(1L);
        verify(postTopicRepository, times(1)).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void verifyFindByIdException() {
        when(postTopicRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        postTopicServiceImpl.findById(1L);
    }

    @Test
    public void verifyDelete() {
        doNothing().when(postTopicRepository).deleteById(any(Long.class));
        postTopicServiceImpl.delete(1L);
        verify(postTopicRepository, times(1)).deleteById(1L);
    }
}
