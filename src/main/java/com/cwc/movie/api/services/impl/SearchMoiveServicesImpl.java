package com.cwc.movie.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.cwc.movie.api.entities.searches.SearchMovies;
import com.cwc.movie.api.services.SearchMoiveServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchMoiveServicesImpl implements SearchMoiveServices {

//	API Endpoints
	private static final String BASE_URL = "https://api.themoviedb.org/3";
	private static final String API_KEY = "?api_key=10d71a2f451efface2a4f5720b2435a2";

//	https://api.themoviedb.org/3/movie/top_rated?api_key=10d71a2f451efface2a4f5720b2435a2
	// Search By Company Name
//	https://api.themoviedb.org/3/search/company?api_key=10d71a2f451efface2a4f5720b2435a2&query=Avatar&page=1
	// Search by its Keyword
//	https://api.themoviedb.org/3/search/keyword?api_key=10d71a2f451efface2a4f5720b2435a2&query=Avater&page=1
	// Search by Collection
//	https://api.themoviedb.org/3/search/collection?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=avvavvvv&page=1
	// Search by Movie Name
//	https://api.themoviedb.org/3/search/movie?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=hellllllllll&page=1&include_adult=false&region=hindi&year=2000&primary_release_year=1999
	// Search Multi Search
//	https://api.themoviedb.org/3/search/multi?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=hhhhhh&page=1&include_adult=false&region=hindi
	// Search People
//	https://api.themoviedb.org/3/search/person?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=hhhhh&page=1&include_adult=false&region=hindi
	// Search TV Show
//	https://api.themoviedb.org/3/search/tv?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&page=1&query=hhhhhhh&include_adult=false&first_air_date_year=200000
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	private ResponseEntity<String> response;

	@Override
	public List<SearchMovies> searchMovieWithKeyword(String keyword) {
		try {
//		String url = "https://api.themoviedb.org/3/search/keyword?api_key=10d71a2f451efface2a4f5720b2435a2&query=Drive&page=1";
			List<SearchMovies> searchMoviesList = webClient.build().get()
					.uri(BASE_URL + "/search/keyword" + API_KEY + "&query=" + keyword + "&page=1").retrieve()
					.bodyToFlux(SearchMovies.class).collectList().block();
			return searchMoviesList;
		} catch (Exception e) {
			log.error("Something went wrong, while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<SearchMovies> searchMovieWithCompanyName(String query) {
		try {
			// https://api.themoviedb.org/3/search/company?api_key=10d71a2f451efface2a4f5720b2435a2&query=Avatar&page=1

			List<SearchMovies> searchMoviesCompanyList = webClient.build().get()
					.uri(BASE_URL + "/search/company" + API_KEY + "&language=en-US" + "&query=" + query + "&page=1")
					.retrieve().bodyToFlux(SearchMovies.class).collectList().block();
			return searchMoviesCompanyList;

		} catch (Exception e) {
			log.error("Something went wrong,while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<SearchMovies> searchMovieWithCollections(String query) {
		try {
			// https://api.themoviedb.org/3/search/collection?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=Drive&page=1
			List<SearchMovies> searchMoviesCompanyList = webClient.build().get()
					.uri(BASE_URL + "search/collection" + API_KEY + "&language=en-US" + "&query=" + query + "&page=1")
					.retrieve().bodyToFlux(SearchMovies.class).collectList().block();
			return searchMoviesCompanyList;
		} catch (Exception e) {
			log.error("Something went wrong, while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<SearchMovies> searchMovieWithMovieName(String keyword) {
		try {
			
			// https://api.themoviedb.org/3/search/movie?api_key=10d71a2f451efface2a4f5720b2435a2&language=en-US&query=Drive&page=1&include_adult=false&region=Hindi&year=2022&primary_release_year=2021
						List<SearchMovies> searchMoviesNameList = webClient.build().get()
								.uri(BASE_URL + "search/movie" + API_KEY + "&language=en-US" + "&query=" + keyword + "&page=1"+ "&include_adult=false")
								.retrieve().bodyToFlux(SearchMovies.class).collectList().block();
						return searchMoviesNameList;
					} catch (Exception e) {
						log.error("Something went wrong, while getting from TMDB database");
						throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
					}
							
	}

	@Override
	public Object searchMovieWithMultiSearch(String keyword) {
		try {
			response = restTemplate.getForEntity(BASE_URL + "search/movie" + API_KEY + "&language=en-US" + "&query="
					+ keyword + "&page=1" + "&include_adult=false&region=hindi", String.class);
			String body = response.getBody();
			return body;
		} catch (Exception e) {
			log.error("Something went wrong, while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Object searchPeopleWithKeyword(String keyword) {
		try {
			response = restTemplate.getForEntity(BASE_URL + "search/movie" + API_KEY + "&language=en-US" + "&query="
					+ keyword + "&page=1" + "&include_adult=false&region=hindi", String.class);
			String body = response.getBody();
			return body;
		} catch (Exception e) {
			log.error("Something went wrong, while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Object searchTvShowWithKeyword(String keyword) {
		try {
			response = restTemplate.getForEntity(BASE_URL + "search/movie" + API_KEY + "&language=en-US" + "&page=1"
					+ "&query=" + keyword + "&include_adult=false&first_air_date_year=2000", String.class);
			String body = response.getBody();
			return body;
		} catch (Exception e) {
			log.error("Something went wrong, while getting from TMDB database");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
