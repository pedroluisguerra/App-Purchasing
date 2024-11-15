package com.keepcoding.purchasing_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keepcoding.purchasing_app.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username); 

}
