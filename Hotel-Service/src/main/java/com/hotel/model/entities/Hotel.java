package com.hotel.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("hotel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
}
