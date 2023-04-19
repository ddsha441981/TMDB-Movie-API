package com.cwc.movie.api.entities;

import org.springframework.data.annotation.Id;

//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document()

//@Entity
public class MovieUser {
	@Id
	private String userId;
	private String username;
	private String password;
	private String email;
	private String userRole;
	private boolean isEnabled = true;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
