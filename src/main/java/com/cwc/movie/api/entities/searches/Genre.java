package com.cwc.movie.api.entities.searches;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Genre {
	@JsonProperty("id")
	private int id;

//	@JsonProperty("name")
//	private String name;
}
