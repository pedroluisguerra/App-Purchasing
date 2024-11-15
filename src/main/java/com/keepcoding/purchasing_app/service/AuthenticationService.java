package com.keepcoding.purchasing_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keepcoding.purchasing_app.dto.LoginUserDto;
import com.keepcoding.purchasing_app.dto.RegisterUserDto;
import com.keepcoding.purchasing_app.entity.User;
import com.keepcoding.purchasing_app.repository.UserRepository;

@Service
public class AuthenticationService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
        ) {
            this.authenticationManager = authenticationManager;
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }
    
    public User signup(RegisterUserDto input) {
        var user = new User();
            user.setUsername(input.getUsername());            
            user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }
    
    public User authenticate(LoginUserDto input) {
    	try { 
    		authenticationManager.authenticate( 
    				new UsernamePasswordAuthenticationToken( 
    						input.getUsername(), 
    						input.getPassword() 
    						) 
    				); 
    		return userRepository.findByUsername(input.getUsername()) .
    				orElseThrow(() -> new UsernameNotFoundException("User not found")); 
    		} catch (AuthenticationException e) { 
    			throw new RuntimeException("Authentication failed", e); 
    			}
    }
    
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
