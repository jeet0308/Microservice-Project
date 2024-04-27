package com.userservice.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;
import com.userservice.external.service.HotelService;
import com.userservice.external.service.RatingService;
import com.userservice.model.entities.Hotel;
import com.userservice.model.entities.Rating;
import com.userservice.model.entities.User;
import com.userservice.repositorites.UserRepo;
import com.userservice.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}



	@Override
	public User createUser(User user) {
		user.setId(UUID.randomUUID().toString());
		User save = this.userRepo.save(user);
		return save;
	}



	@Override
	public List<User> getAllUser() {
		return this.userRepo.findAll();
	}

	
	

	@Override
	public User getUser(String id)  {
		User user= this.userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found:"+id));
//		Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/api/rating/users/34c9b63a-7437-4b89-9578-36cbe62d3f9f", Rating[].class);
//		 List<Rating> ratinglist = Arrays.stream(ratings).toList();
		
		 List<Rating> ratinglist = this.ratingService.getRatingByUserId(user.getId());
		 
		 List<Rating> list = ratinglist.stream().map(rating -> {
//			 ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotels/408c6f0e-93b4-4739-89de-83a42214f9d2", Hotel.class);
//			 Hotel hotel = forEntity.getBody();
			 
			 Hotel hotel = hotelService.getHotelById(rating.getHotelId());
			 rating.setHotel(hotel);
			 return rating;
		 }).collect(Collectors.toList());
		 
		user.setRating(ratinglist);
		 return user;
	}

}
