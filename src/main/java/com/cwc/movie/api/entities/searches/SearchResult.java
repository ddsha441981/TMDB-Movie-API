package com.cwc.movie.api.entities.searches;

import java.util.ArrayList;

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
public class SearchResult {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("logo_path")
	private String logo_path;
	@JsonProperty("origin_country")
	private String origin_country;
	@JsonProperty("adult")
	private boolean adult;
	@JsonProperty("backdrop_path")
	private String backdrop_path;
	@JsonProperty("original_language")
	private String original_language;
	@JsonProperty("original_name")
	private String original_name;
	@JsonProperty("overview")
	private String overview;
	@JsonProperty("poster_path")
	private String poster_path;

	@JsonProperty("genre_ids") 
	private ArrayList<Genre> genre_ids;
	
	@JsonProperty("original_title")
	String original_title;
	@JsonProperty("popularity")
	private double popularity;
	@JsonProperty("release_date")
	private String release_date;
	@JsonProperty("title")
	private String title;
	@JsonProperty("video")
	private boolean video;
	@JsonProperty("vote_average")
	private double vote_average;
	@JsonProperty("vote_count")
	private int vote_count;

}
