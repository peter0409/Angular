package com.nivtek.angular.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivtek.angular.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);

}
