package com.rating.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rating.Service.RatingService;
import com.rating.model.entities.Rating;
import com.rating.repositories.RatingRepo;
@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepo ratingRepo;
	

	@Override
	public Rating createRating(Rating rating) {
		System.out.println(rating.getId());
		return	this.ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getRatingByuserId(String userId) {
		List<Rating> ratingByUserId = this.ratingRepo.getRatingByUserId(userId);
		return ratingByUserId;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepo.getRatingByHotelId(hotelId);
	}

	@Override
	public List<Rating> getRating() {
		return this.ratingRepo.findAll();
	}
	

}
