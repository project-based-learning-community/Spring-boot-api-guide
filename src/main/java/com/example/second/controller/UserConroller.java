package com.example.second.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.second.model.User;
import com.example.second.repository.UserRepository;
import com.example.second.service.UserService;

@RestController
@RequestMapping("/user")
public class UserConroller {
  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @PostMapping("/register")
  public User createUser(@RequestBody User user) {
    return userService.registerUser(user);

  }

  @PutMapping("/update/{userId}")
  public User updateUserById(@RequestBody User user, @PathVariable Long userId) throws Exception {

    return userService.updateUser(user, userId);

  }

  @GetMapping("/find/{userEmail}")
  public User findUserByEmail(@PathVariable String userEmail) {
    return userService.findUserByEmail(userEmail);
  }

  @GetMapping("/follow/{user1}/to/{user2}")
  public User followUser1ToUser2(@PathVariable Long user1, @PathVariable Long user2) throws Exception {
    return userService.followUser(user1, user2);
  }

  @DeleteMapping("/delete/{userId}")
  public String deleteUserById(@PathVariable Long userId) throws Exception {
    return userService.deleteUserById(userId);
  }

  @GetMapping("/search")
  public List<User> searchUser(@RequestParam("query") String query) { // here we use @RequestParam not @PathVariable
    return userService.searchUser(query);
  }

  /** Testing end-point- not for any use */
  @GetMapping
  public String welcome() {
    return "welcome to my first api developement project";
  }

  @GetMapping("/all")
  public List<User> getUserAll() {
    List<User> user = userRepository.findAll();
    return user;
  }

  @GetMapping("/help")
  public String help() {
    return "help me in my first api";
  }

}
