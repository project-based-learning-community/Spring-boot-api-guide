package com.example.second.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.second.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("select p from Post p where p.user.id=:userId")
  List<Post> findPostByUserId(Long userId);

}
