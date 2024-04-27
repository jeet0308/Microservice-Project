package com.userservice.repositorites;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.userservice.model.entities.User;

public interface UserRepo extends MongoRepository<User, String> {

}
