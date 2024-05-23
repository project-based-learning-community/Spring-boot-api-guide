package com.example.second.service;

import java.util.List;

import com.example.second.model.Post;

public interface PostService {

  Post createNewPost(Post post, Long userId) throws Exception;

  String deletePost(Long postId, Long userId) throws Exception;

  List<Post> findAllPostByUserId(Long userId) throws Exception;

  Post findPostById(Long postId) throws Exception;

  List<Post> findAllPost();

  Post likePost(Long postId, Long userId) throws Exception;

}
