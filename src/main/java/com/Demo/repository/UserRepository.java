package com.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
