package com.hhyusein.Blog.repository;

import com.hhyusein.Blog.model.PostTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostTopicRepository extends JpaRepository<PostTopic, Long> {

}
