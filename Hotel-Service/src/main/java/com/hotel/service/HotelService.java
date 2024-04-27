package com.hotel.service;

import java.util.List;

import com.hotel.model.entities.Hotel;

public interface HotelService {

	public Hotel createHotel(Hotel hotel);
	
	public List<Hotel> getAllHotel();
	
	public Hotel getHotel(String id);
}
