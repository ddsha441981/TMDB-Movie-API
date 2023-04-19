package com.cwc.movie.api.entities.trending;

import java.util.ArrayList;

import com.cwc.movie.api.entities.searches.SearchResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RootPages {
	 public int page;
	    public ArrayList<TrendingMovies> trendingMoviesResult;
	    public int total_pages;
	    public int total_results;
}
