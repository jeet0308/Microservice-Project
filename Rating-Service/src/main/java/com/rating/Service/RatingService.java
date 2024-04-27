package com.rating.Service;

import java.util.List;

import com.rating.model.entities.Rating;

public interface RatingService {

	
	public Rating createRating(Rating rating);
	
	public List<Rating> getRatingByuserId(String userId);
	
	public List<Rating> getRatingByHotelId(String hotelId);
	
	public List<Rating> getRating();
	
}
