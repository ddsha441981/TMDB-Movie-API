package com.cwc.movie.api.services;

import java.util.List;

import com.cwc.movie.api.entities.searches.SearchMovies;

public interface SearchMoiveServices {
	
//	Search Companies
	public List<SearchMovies> searchMovieWithCompanyName(String query);
//	Search Collections
//	Search Keywords
//	Search Movies
//	Multi Search
//	Search People
//	Search TV Shows
	public Object searchTvShowWithKeyword(String keyword);
//    

}   
  