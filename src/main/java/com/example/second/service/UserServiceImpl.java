package com.example.second.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.second.model.User;
import com.example.second.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User registerUser(User user) {
    User newUser = new User();
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(user.getPassword());
    User saveUser = userRepository.save(newUser);

    return saveUser;
  }

  @Override
  public User findUserById(Long userId) throws Exception {
    Optional<User> findUser = userRepository.findById(userId);
    if (findUser.isPresent()) {
      return findUser.get();
    }
    throw new Exception("user is not present with id" + userId);
  }

  @Override
  public User findUserByEmail(String email) {
    User user = userRepository.findByEmail(email);
    return user;

  }

  @Override
  public User followUser(Long userId1, Long userId2) throws Exception {
    User user1 = findUserById(userId1);
    User user2 = findUserById(userId2);
    // user1 want to followed user2
    user1.getFollowings().add(user2.getId());
    user2.getFollowers().add(user1.getId());
    userRepository.save(user1);
    userRepository.save(user2);

    return user1;

  }

  /**
   * here {
   * <h3>user</h3> is new user body you are passing throw frontent but
   * <h3>userId</h3> => represent you olderUser where you want to change data
   * through
   * <h3>user</h3>
   */
  @Override
  public User updateUser(User user, Long userId) throws Exception {
    Optional<User> user1 = userRepository.findById(userId);
    if (user1.isEmpty())
      throw new Exception("user not found with id" + userId);

    User olderUser = user1.get();
    if (user.getFirstName() != null) {
      olderUser.setFirstName(user.getFirstName());

    }
    if (user.getLastName() != null) {
      olderUser.setLastName(user.getLastName());

    }
    if (user.getEmail() != null) {
      olderUser.setEmail(user.getEmail());

    }
    if (user.getPassword() != null) {
      olderUser.setPassword(user.getPassword());

    }

    User modifyUser = userRepository.save(olderUser);
    return modifyUser;

  }

  @Override
  public List<User> searchUser(String query) {
    List<User> user = userRepository.searchUser(query);
    return user;

  }

  @Override
  public String deleteUserById(Long userId) {
    userRepository.deleteById(userId);

    return "user deleted successfully with id- " + userId;
  }

}
