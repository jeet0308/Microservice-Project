package com.gateway.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthResponse {

	private String userId;

	private String accessToken;

	private String refreshToken;

	private long expireAt;
	
	private Collection<String> authorities;

}
