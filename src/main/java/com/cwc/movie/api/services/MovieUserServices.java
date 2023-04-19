package com.cwc.movie.api.services;

import com.cwc.movie.api.dto.MovieUserDto;
import com.cwc.movie.api.exception.UserAlreadyExistException;

import java.util.List;

public interface MovieUserServices {
	
	//Create User
	public MovieUserDto createNewUser(MovieUserDto movieUserDto) throws UserAlreadyExistException;
	//Update User
	MovieUserDto updateUser(MovieUserDto movieUserDto, String userId);
	//Get - Single User
	MovieUserDto getUserById(String userId);
	//Get - All User
	List<MovieUserDto> getAllUsers();
	//Get - Delete User
	void deleteUser(String userId);

}
