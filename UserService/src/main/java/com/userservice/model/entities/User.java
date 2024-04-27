package com.userservice.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
@Builder
public class User {

	@Id
	private String id;
	
	private String name;
	
	private String mobileNumber;
	
	@Transient
	private List<Rating> rating = new ArrayList<Rating>() ;
	
	
}
