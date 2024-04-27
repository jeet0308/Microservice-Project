package com.userservice.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userservice.model.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	
//	public Rating getRating() ;
	
	@GetMapping("/api/rating/users/{userId}")
	public List<Rating> getRatingByUserId(@PathVariable String userId);
}
