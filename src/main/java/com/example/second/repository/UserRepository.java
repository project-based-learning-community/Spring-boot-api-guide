package com.example.second.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.second.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
