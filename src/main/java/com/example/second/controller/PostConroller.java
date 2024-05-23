package com.example.second.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.second.model.Post;
import com.example.second.response.ApiReposne;
import com.example.second.service.PostService;

@RestController
public class PostConroller {

  @Autowired
  PostService postService;

  @PostMapping("/{userId}/post/create")
  public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Long userId) throws Exception {
    Post createPost = postService.createNewPost(post, userId);
    return new ResponseEntity<>(createPost, HttpStatus.OK);

  }

  @DeleteMapping("/{userId}/post/delete/{postId}")
  public ResponseEntity<ApiReposne> deletePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {

    String message = postService.deletePost(postId, userId);

    return new ResponseEntity<>(new ApiReposne(message, true), HttpStatus.ACCEPTED);

  }

  @GetMapping("{userId}/post/all")
  public ResponseEntity<List<Post>> findAllPostByUserId(@PathVariable Long userId) throws Exception {
    List<Post> postList = postService.findAllPostByUserId(userId);
    return new ResponseEntity<>(postList, HttpStatus.OK);
  }

  @GetMapping("/post/{postId}")
  public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws Exception {
    Post post = postService.findPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("/post/all")
  public ResponseEntity<List<Post>> findAllPost() {
    List<Post> post = postService.findAllPost();
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("{userId}/post/{postId}/like/")
  public ResponseEntity<Post> likePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
    Post post = postService.likePost(postId, userId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

}
