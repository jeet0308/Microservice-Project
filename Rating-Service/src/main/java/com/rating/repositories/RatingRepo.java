package com.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rating.model.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, String> {
	
	@Query(value = "SELECT R FROM Rating R where R.userId= :userId")
	public List<Rating> getRatingByUserId(String userId);
	
	@Query(value = "SELECT R FROM Rating R where R.hotelId= :hotelId")
	public List<Rating> getRatingByHotelId(String hotelId);

}
