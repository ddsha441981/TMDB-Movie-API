package com.cwc.movie.api.services.impl;

import com.cwc.movie.api.dto.MovieUserDto;
import com.cwc.movie.api.entities.MovieUser;
import com.cwc.movie.api.repository.MovieUserRepository;
import com.cwc.movie.api.services.MovieUserServices;
import com.cwc.movie.api.exception.UserAlreadyExistException;
import com.cwc.movie.api.exception.ResourceNotFoundException;
import com.cwc.movie.api.utils.UserIdGenerate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,MovieUserServices {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MovieUserRepository movieUserRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//get Username from database
		 MovieUser movieUser = movieUserRepository.findByUsername(username)
				 .orElseThrow(()-> new UsernameNotFoundException(username+"not found"));
		
		var authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(movieUser.getUserRole()));
		return new User(movieUser.getUsername(),movieUser.getPassword(),authorities);
	}
	
	@Override
	public MovieUserDto createNewUser(MovieUserDto movieUserDto) throws UserAlreadyExistException {
		//extract data from controller request
		String username = movieUserDto.getUsername();
		//check existing user in database
		boolean existedUsername = movieUserRepository.existsByUsername(username);
		if(existedUsername) {
			throw new UserAlreadyExistException();
		}
		//create new user and save
		MovieUser movieUser = this.modelMapper.map(movieUserDto, MovieUser.class);
		// encoded the password
		String encodedPassword = this.passwordEncoder.encode(movieUser.getPassword());
		System.out.println("Encoded Password is {} " + encodedPassword);
		movieUser.setPassword(encodedPassword);
		//set userId
		String generateId = UserIdGenerate.generateId();
		System.out.println("Generated Id,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, {} " + generateId);
		movieUser.setUserId(generateId);
		MovieUser savedUser = movieUserRepository.save(movieUser);
		return this.modelMapper.map(savedUser, MovieUserDto.class);
	}

	@Override
	public MovieUserDto updateUser(MovieUserDto movieUserDto, String userId) {
		MovieUser movieUser = this.movieUserRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(userId + "Resource not found!!!!"));
		movieUser.setUsername(movieUserDto.getUsername());
		movieUser.setEmail(movieUserDto.getEmail());
		movieUser.setPassword(movieUserDto.getPassword());
		//now update user
		MovieUser updatedUser = this.movieUserRepository.save(movieUser);
		return this.modelMapper.map(updatedUser,MovieUserDto.class);
	}

	@Override
	public MovieUserDto getUserById(String userId) {
		MovieUser movieUser = this.movieUserRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return this.modelMapper.map(movieUser,MovieUserDto.class);
	}

	@Override
	public List<MovieUserDto> getAllUsers() {
		List<MovieUser> users = this.movieUserRepository.findAll();
		List<MovieUserDto> movieUserDtoList = users.stream().map((user) -> this.modelMapper
						.map(user, MovieUserDto.class))
				.collect(Collectors.toList());
		return movieUserDtoList;
	}

	@Override
	public void deleteUser(String userId) {
		MovieUser movieUser = this.movieUserRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User id with this userId not found"));
		this.movieUserRepository.delete(movieUser);
	}
}
