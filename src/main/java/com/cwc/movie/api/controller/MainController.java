package com.cwc.movie.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v4")
public class MainController {

	@GetMapping("/")
	public String avavilAll() {
		return "Use All";
	}
	
	@GetMapping("/user")
	public String authenticatedForUser() {
		return "Only For User";
	}
	
	@GetMapping("/admin")
	public String authenticatedForAdmin() {
		return "Only For Admin";
	}
}
