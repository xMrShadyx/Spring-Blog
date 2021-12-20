package com.hhyusein.Blog.service.impl.func;

import com.hhyusein.Blog.exception.DuplicateRecordException;
import com.hhyusein.Blog.model.PostTopic;
import com.hhyusein.Blog.repository.PostTopicRepository;
import com.hhyusein.Blog.service.PostTopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostTopicFuncTest {

    @Autowired
    private PostTopicService postTopicService;

    @Autowired
    private PostTopicRepository postTopicRepository;

    @Test
    public void verifyUpdate() {
        PostTopic postTopic = PostTopic.builder()
                .topicName("testTopic")
                .build();

        PostTopic savedPost = postTopicRepository.save(postTopic);
        PostTopic expected = PostTopic.builder()
                .topic_id(savedPost.getTopic_id())
                .topicName("anotherTest")
                .build();

        PostTopic actual = postTopicService.update(expected, savedPost.getTopic_id());
        assertThat(expected.getTopic_id(), is(actual.getTopic_id()));
        assertThat(expected.getTopicName(), is(actual.getTopicName()));
    }

    @Test
    public void verifyFindId() {
        PostTopic postTopic = PostTopic.builder()
                .topicName("Test")
                .build();

        PostTopic expected = postTopicRepository.save(postTopic);
        PostTopic actual = postTopicService.findById(expected.getTopic_id());
        assertEquals(expected.getTopic_id(), actual.getTopic_id());
        assertEquals(expected.getTopicName(), actual.getTopicName());


    }

    @Test
    public void verifyFindAll() {
        postTopicRepository.saveAll(Arrays.asList(
                PostTopic.builder().topicName("Test").build(),
                PostTopic.builder().topicName("Test 2").build()
        ));

        List<PostTopic> actual = postTopicService.findAll();
        assertThat(actual.size(), is(2));
    }

    @Test
    public void verifySave() {
        PostTopic savedPost = postTopicService.save(PostTopic.builder().topicName("Test").build());
        Optional<PostTopic> actualPost = postTopicRepository.findById(savedPost.getTopic_id());

        assertTrue(actualPost.isPresent());
    }

    @Test
    public void verifyDeleteById() {
        PostTopic savedTopic = postTopicRepository.save(PostTopic.builder().topicName("Test").build());
        postTopicService.delete(savedTopic.getTopic_id());
        List<PostTopic> actual = postTopicRepository.findAll();

        assertThat(actual.size(), is(0));
    }

    @Test(expected = DuplicateRecordException.class)
    public void verifyDuplicateRecordException() {
        postTopicService.save(PostTopic.builder().topicName("Test").build());
        postTopicService.save(PostTopic.builder().topicName("Test").build());

    }


}
