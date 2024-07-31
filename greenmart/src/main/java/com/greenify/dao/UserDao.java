package com.greenify.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenify.entities.User;

public interface UserDao extends JpaRepository<User, Long> {

}
