package com.cwc.movie.api.controller;

import com.cwc.movie.api.dto.AuthResponse;
import com.cwc.movie.api.entities.MovieUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.movie.api.config.security.JwtProvider;
import com.cwc.movie.api.dto.MovieUserDto;
import com.cwc.movie.api.dto.AuthRequest;
import com.cwc.movie.api.services.MovieUserServices;
import com.cwc.movie.api.exception.UserAlreadyExistException;

@RestController
@RequestMapping("/api/v4/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired 
	private MovieUserServices movieUserServices;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest){
		//get user details
		//this.authenticateUser(authRequest.getUsername(),authRequest.getPassword());
		UserDetails username = userDetailsService.loadUserByUsername(authRequest.getUsername());
		try{
			if(passwordEncoder.matches(authRequest.getPassword(),username.getPassword()) &&
					username.getUsername().equals(authRequest.getUsername())) {
				//Generate Token
				String generatedToken = jwtProvider.generateToken(authRequest.getUsername());
				AuthResponse authResponse = new AuthResponse();
				authResponse.setToken(generatedToken);
				authResponse.setUser(this.modelMapper.map(username, MovieUserDto.class));
				return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
			}
		}catch(BadCredentialsException e){
			e.getMessage();
				throw new BadCredentialsException("Username and password invalid");
			}
		throw new BadCredentialsException("Username and password invalid");
	}

	//Authenticated before login and generations token
//	private void authenticateUser(String username, String password) {
//		var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//		try {
//			this.authenticationManager.authenticate(authenticationToken);
//		}catch (BadCredentialsException e){
//			throw new BadCredentialsException("Invalid user credentials");
//		}
//	}

	@PostMapping("/create-user")
	public ResponseEntity<MovieUserDto> addUser(@RequestBody MovieUserDto movieUserDto) throws UserAlreadyExistException{
		MovieUserDto createdNewUser = movieUserServices.createNewUser(movieUserDto);
		return new ResponseEntity<MovieUserDto>(createdNewUser,HttpStatus.CREATED);
		
	}
	
}
