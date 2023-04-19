package com.cwc.movie.api.services.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.cwc.movie.api.services.TrendingMovieServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrendingMovieServicesImpl implements TrendingMovieServices {

	private static final String BASE_URL = "https://api.themoviedb.org/3";
	private static final String API_KEY = "?api_key=10d71a2f451efface2a4f5720b2435a2";

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webClient;
	private ResponseEntity<String> response;
//	https://api.themoviedb.org/3/trending/all/day?api_key=10d71a2f451efface2a4f5720b2435a2

	@Override
	public Object trendingMoviesList() {

//		String url = "https://api.themoviedb.org/3/trending/all/day?api_key=10d71a2f451efface2a4f5720b2435a2";
//		webClient.build()
//		.get()
//		.uri(url)
//		.retrieve()
//		.bodyToMono(null)
//		.block();

		try {
			response = restTemplate.getForEntity(BASE_URL + "trending/all/day" + API_KEY, String.class);
			System.out.println(response + "--------------------------------------------");
			String body = response.getBody();

//			ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(BASE_URL + "trending/all/day" + API_KEY, Object[].class);
//		System.out.println(responseEntity + ".......................");
//			Object[] objects = responseEntity.getBody();
//			MediaType contentType = responseEntity.getHeaders().getContentType();
//			HttpStatus statusCode = responseEntity.getStatusCode();
			return body;
		} catch (Exception ex) {
			log.error("Something went worng while getting data from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
