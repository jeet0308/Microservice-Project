package com.rating.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled =  true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
//	private static final String [] SWAGGER_WHITELIST_STRINGS= {
//			"/swagger-ui/**",
//			"/v3/api-docs/**",".swagger-resources/**",
//			"swagger-resources"
//	};

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests()
//					.requestMatchers().permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.oauth2ResourceServer()
					.jwt();
		
		return httpSecurity.build();
	}
}
