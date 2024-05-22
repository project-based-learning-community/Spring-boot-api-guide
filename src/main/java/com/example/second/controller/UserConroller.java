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
import org.springframework.web.bind.annotation.RestController;

import com.example.second.model.User;
import com.example.second.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserConroller {
  @Autowired
  UserRepository userRepository;

  @GetMapping
  public String welcome() {
    return "welcome to my first api developement project";
  }

  @PostMapping("/create")
  public User createUser(@RequestBody User user) {
    User createUser = new User();
    createUser.setFirstName(user.getFirstName());
    createUser.setLastName(user.getLastName());
    createUser.setEmail(user.getEmail());
    createUser.setAge(user.getAge());
    User newUser = userRepository.save(createUser);
    return newUser;
  }

  @PutMapping("/update/{id}")
  public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {
    Optional<User> searchUser = userRepository.findById(id);
    if (searchUser.isEmpty()) {
      throw new Exception("user not found with this Id" + id);
    }
    User oldUser = searchUser.get();
    if (user.getFirstName() != null) {
      oldUser.setFirstName(user.getFirstName());
    }
    if (user.getLastName() == null) {
      oldUser.setLastName(user.getLastName());
    }
    User resultUser = userRepository.save(oldUser);
    return resultUser;

  }

  @DeleteMapping("/delete/{id}")
  public String deleteUser(@PathVariable Long id) throws Exception {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty())
      throw new Exception("user is not found with id" + id);
    userRepository.delete(user.get());
    return "User successfully deleted with id - " + id;

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
