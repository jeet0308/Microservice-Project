package com.hotel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.model.entities.Hotel;

public interface HotelRepo extends MongoRepository<Hotel, String> {

}
