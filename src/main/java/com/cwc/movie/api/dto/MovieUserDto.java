package com.cwc.movie.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MovieUserDto {
	private String userId;
	private String username;
	private String password;
	private String email;
	private String userRole;
	private boolean isEnabled = true;
}
