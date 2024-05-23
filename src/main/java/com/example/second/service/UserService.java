package com.example.second.service;

import java.util.List;

import com.example.second.model.User;

public interface UserService {
  public User registerUser(User user);

  public User findUserById(Long userId) throws Exception;

  public User findUserByEmail(String email);

  public User followUser(Long userId1, Long userId2) throws Exception;

  public User updateUser(User user, Long userId) throws Exception;

  public List<User> searchUser(String query);

  public String deleteUserById(Long userId);

}
