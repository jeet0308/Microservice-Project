package com.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	private static final String [] SWAGGER_WHITELIST_STRINGS= {
			"/swagger-ui/**",
			"/v3/api-docs/**",".swagger-resources/**",
			"swagger-resources"
	};
	

	@Bean
	public SecurityFilterChain filrChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET,SWAGGER_WHITELIST_STRINGS).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt();

		return httpSecurity.build();
	}
}
