package com.keepcoding.purchasing_app.serviceImpl;

import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.entity.User;
import com.keepcoding.purchasing_app.repository.UserRepository;
import com.keepcoding.purchasing_app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Override
	public void createNewUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

}
