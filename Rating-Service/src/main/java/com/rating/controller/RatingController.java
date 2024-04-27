package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.Service.RatingService;
import com.rating.model.entities.Rating;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public Rating createRating(@RequestBody Rating rating) {
		return this.ratingService.createRating(rating);
	}
	
	@GetMapping("/users/{userId}")
	public List<Rating> getRatingByUserId(@PathVariable String userId){
		return this.ratingService.getRatingByuserId(userId);
	}
}
