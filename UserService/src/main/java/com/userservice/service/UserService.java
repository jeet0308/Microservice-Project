package com.userservice.service;

import java.util.List;

import com.userservice.model.entities.User;

public interface UserService {

	
	public User createUser(User user);
	
	public List<User> getAllUser();
	
	public User getUser(String id);
}
