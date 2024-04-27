package com.hotel.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.entities.Hotel;
import com.hotel.repositories.HotelRepo;
import com.hotel.service.HotelService;
@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setId(UUID.randomUUID().toString());
		return this.hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return this.hotelRepo.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return this.hotelRepo.findById(id).orElseThrow(()-> new RuntimeException("Hotel Not Found with :"+id));
	}

}
