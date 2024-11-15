package com.keepcoding.purchasing_app.service;

import com.keepcoding.purchasing_app.entity.User;

public interface UserService {	
	
	void createNewUser(User user);
	
	User saveUser(User user); 

}
