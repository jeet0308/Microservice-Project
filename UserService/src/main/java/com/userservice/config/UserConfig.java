package com.userservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.userservice.config.Interceptor.RestTemplateInterceptor;

@Configuration
public class UserConfig {

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
	

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(
				new RestTemplateInterceptor(manager(clientRegistrationRepository, oAuth2AuthorizedClientRepository)));
		restTemplate.setInterceptors(interceptors);
		return restTemplate;

	}

	// Declare bean of OAuth2AuthorizedClient Manager

//	@Bean
//	public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository,
//			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
//
//		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials()
//				.build();
//
//		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
//				clientRegistrationRepository, oAuth2AuthorizedClientRepository);
//
//		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
//		return defaultOAuth2AuthorizedClientManager;
//	}

	@Bean
	public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials()
				.build();
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		return defaultOAuth2AuthorizedClientManager;
	}
	
	
}
