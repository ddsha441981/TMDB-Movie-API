package com.cwc.movie.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.movie.api.services.TrendingMovieServices;

@RestController
@RequestMapping("api/v3/movies/trending")
public class TrendingController {

	@Autowired
	private TrendingMovieServices trendingMovieServices;
	
	@GetMapping("/")
	public ResponseEntity trendingMovieList() {
		
		
		Object trendingMoviesList = this.trendingMovieServices.trendingMoviesList();
		System.out.println("......................................." + trendingMoviesList);
		return  ResponseEntity.ok(trendingMoviesList);
		
	}
	
}
