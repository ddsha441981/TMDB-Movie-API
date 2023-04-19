package com.cwc.movie.api.entities.searches;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMovies {

	@JsonProperty("page")
	private int page;
	@JsonProperty("results")
//	private ArrayList<SearchResult> searchResult;
	private List<SearchResult> searchResult;
	@JsonProperty("total_pages")
	private int total_pages;
	@JsonProperty("total_results")
	private int total_results;
	
	 

}
