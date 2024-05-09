package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.entities.Hotel;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize(" hasAuthority('Admin')")
	@PostMapping
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return this.hotelService.createHotel(hotel);
	}
	@PreAuthorize("hasAuthority('SCOPE_internal') ")
	@GetMapping
	public List<Hotel> getAllHotel(){
		return this.hotelService.getAllHotel();
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') ")
	@GetMapping("/{id}")
	public Hotel getHotelById(@PathVariable String id) {
		return hotelService.getHotel(id);
	}
}
