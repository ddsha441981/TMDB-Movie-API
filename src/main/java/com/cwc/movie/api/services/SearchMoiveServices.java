package com.cwc.movie.api.services;

import java.util.List;

import com.cwc.movie.api.entities.searches.SearchMovies;

public interface SearchMoiveServices {
	
//	Search Companies
	public List<SearchMovies> searchMovieWithCompanyName(String query);
//	Search Collections	public List<SearchMovies> searchMovieWithCollections(String query);
//	Search Keywords	public List<SearchMovies> searchMovieWithKeyword(String keyword);
//	Search Movies	public List<SearchMovies> searchMovieWithMovieName(String keyword);
//	Multi Search	public Object searchMovieWithMultiSearch(String keyword);
//	Search People	public Object searchPeopleWithKeyword(String keyword);
//	Search TV Shows
	public Object searchTvShowWithKeyword(String keyword);
//    

}   
  