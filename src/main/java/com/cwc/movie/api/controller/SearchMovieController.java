package com.cwc.movie.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.movie.api.entities.searches.SearchMovies;
import com.cwc.movie.api.services.SearchMoiveServices;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("api/v3/movies/search")
public class SearchMovieController {

	@Autowired
	private SearchMoiveServices searchMoiveServices;
	
	@GetMapping("/company-name/{query}")
	public ResponseEntity<List<SearchMovies>> searchMoviesByCompanyName(@PathVariable("query") String query){
		List<SearchMovies> searchMovieWithCompanyName = searchMoiveServices.searchMovieWithCompanyName(query);
		 return ResponseEntity.ok(searchMovieWithCompanyName);
	}
	
	@GetMapping("/by-keyword/{keyword}")
	public ResponseEntity<List<SearchMovies>> searchMoviesKeyword(@PathVariable("keyword") String keyword){
		List<SearchMovies> searchMovieWithKeyword = searchMoiveServices.searchMovieWithKeyword(keyword);
		return ResponseEntity.ok(searchMovieWithKeyword);
//		 return ResponseEntity.ok(searchMovieWithKeyword);
	}
	
	@GetMapping("/by-collections/{keyword}")
	public ResponseEntity<List<SearchMovies>> searchMoviesCollections(@PathVariable("keyword") String keyword){
		List<SearchMovies> searchMovieWithCollections = searchMoiveServices.searchMovieWithCollections(keyword);
		
		return ResponseEntity.ok(searchMovieWithCollections);
	}
	
	@GetMapping("/by-movie-name/{keyword}")
	public  ResponseEntity<List<SearchMovies>> searchMoviesName(@PathVariable("keyword") String keyword){
		List<SearchMovies> searchMovieWithMovieName = searchMoiveServices.searchMovieWithMovieName(keyword);
		 return ResponseEntity.ok(searchMovieWithMovieName);
	}
	
	@GetMapping("/by-movie-multi-search/{keyword}")
	public ResponseEntity searchMoviesMultiSearch(@PathVariable("keyword") String keyword){
		Object searchMovieWithMultiSearch = searchMoiveServices.searchMovieWithMultiSearch(keyword);
		 return ResponseEntity.ok(searchMovieWithMultiSearch);
	}
	
	@GetMapping("/by-people/{keyword}")
	public ResponseEntity searchMoviesByPeople(@PathVariable("keyword") String keyword){
		Object searchMovieWithPeople = searchMoiveServices.searchPeopleWithKeyword(keyword);
		 return ResponseEntity.ok(searchMovieWithPeople);
	}
	
	@GetMapping("/by-tv-show/{keyword}")
	public ResponseEntity searchMoviesByTVShow(@PathVariable("keyword") String keyword){
		Object searchMovieWithTVShow = searchMoiveServices.searchTvShowWithKeyword(keyword);
		 return ResponseEntity.ok(searchMovieWithTVShow);
	}
}
