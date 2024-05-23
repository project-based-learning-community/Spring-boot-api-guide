package com.example.second.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.second.model.Post;
import com.example.second.model.User;
import com.example.second.response.ApiReposne;
import com.example.second.service.UserService;

@RestController
@RequestMapping("/user")
public class UserConroller {

  @Autowired
  UserService userService;

  // @Autowired
  // PostService postService;

  @PostMapping("/register")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User ctUser = userService.registerUser(user);
    return new ResponseEntity<>(ctUser, HttpStatus.OK);

  }

  @PutMapping("/update/{userId}")
  public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable Long userId) throws Exception {

    User updateUser = userService.updateUser(user, userId);
    return new ResponseEntity<>(updateUser, HttpStatus.OK);

  }

  @GetMapping("/find/{userEmail}")
  public ResponseEntity<User> findUserByEmail(@PathVariable String userEmail) {
    User user = userService.findUserByEmail(userEmail);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/follow/{user1}/to/{user2}")
  public ResponseEntity<User> followUser1ToUser2(@PathVariable Long user1, @PathVariable Long user2) throws Exception {
    User user = userService.followUser(user1, user2);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<ApiReposne> deleteUserById(@PathVariable Long userId) throws Exception {
    String message = userService.deleteUserById(userId);
    ApiReposne res = new ApiReposne(message, true);
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @GetMapping("/search")
  public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) { // here we use @RequestParam not
    // @PathVariable
    List<User> listUser = userService.searchUser(query);
    return new ResponseEntity<>(listUser, HttpStatus.OK);
  }

  @GetMapping("{userId}/post/{postId}/save")
  public ResponseEntity<Post> savePost(@PathVariable Long postId, @PathVariable Long userId) throws Exception {
    Post post = userService.savePost(postId, userId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUserAll() {
    List<User> listUser = userService.getUserAll();
    return new ResponseEntity<>(listUser, HttpStatus.OK);
  }

}
