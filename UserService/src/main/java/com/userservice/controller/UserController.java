package com.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.model.entities.User;
import com.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private static final Logger LOG =  LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
//	@PreAuthorize(" hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user2 = this.userService.createUser(user);
		return new ResponseEntity<User>(user2, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<User> getAllUser(){
		return this.userService.getAllUser();
	}
	
	int retryCount=1;
	@GetMapping("/{id}")
	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelBreaker")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelBreaker")
//	@RateLimiter(name = "ratingHotelRatingLimiter", fallbackMethod = "ratingHotelBreaker")
	public User getUser(@PathVariable String id) {
		LOG.info("Retry Count {}",retryCount);
		retryCount++;
		return this.userService.getUser(id);
	}
	
	
	//Creating fall back method
	public User  ratingHotelBreaker(String userId,Exception ex) {
		LOG.info("Fallback is excuted because some service down:{}",ex.getMessage());
		ex.printStackTrace();
		return User.builder()
				.name("dummy")
				.mobileNumber("56356464")
				.id("541556456")
				.build();
	}
}
