package com.example.second.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.second.model.Post;
import com.example.second.model.User;
import com.example.second.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserService userService;

  @Override
  public Post createNewPost(Post post, Long userId) throws Exception {
    User user = userService.findUserById(userId);

    Post newPost = new Post();
    newPost.setDescription(post.getDescription());
    newPost.setImage(post.getImage());
    newPost.setVideo(post.getVideo());
    newPost.setUser(user);
    newPost.setCreatedAt(LocalDateTime.now());
    return postRepository.save(newPost);
  }

  @Override
  public String deletePost(Long postId, Long userId) throws Exception {
    Post post = findPostById(postId);

    User user = userService.findUserById(userId);

    if (post.getUser().getId() == user.getId()) {
      postRepository.deleteById(postId);
    } else {
      throw new Exception("post is not created by you, so can't delete them.");
    }

    return "post deleted successFully ";
  }

  @Override
  public List<Post> findAllPostByUserId(Long userId) throws Exception {
    return postRepository.findPostByUserId(userId);
  }

  @Override
  public Post findPostById(Long postId) throws Exception {
    Optional<Post> post = postRepository.findById(postId);

    if (post.isEmpty())
      throw new Exception("Post is not found with id " + postId);

    return post.get();
  }

  @Override
  public List<Post> findAllPost() {
    return postRepository.findAll();
  }

  @Override
  public Post likePost(Long postId, Long userId) throws Exception {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    if (post.getLike().contains(user)) {
      post.getLike().remove(user);
    } else {
      post.getLike().add(user);
    }

    return postRepository.save(post);
  }

}
