package com.cwc.movie.api.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get Token from request
		String token = getTokenFromRequest(request);
		// Validate Token using JwtProvider
		if (token != null && jwtProvider.validateToken(token)) {
			// Get User name from token
			String username = jwtProvider.getUsernameFromToken(token);
			// GET User details
			UserDetails userDetails = detailsService.loadUserByUsername(username);
			// Create authentication object to pass spring context
			var auth = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// Set User to Spring Content
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		//pass the request
		filterChain.doFilter(request, response);

	}

	private String getTokenFromRequest(HttpServletRequest request) {
		// Extract authentication header
		var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		// Bearer {JWT}

		// Check whether it starts with `Bearer ` or not
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return "Jwt token not started with Bearer try again!!!";
	}

}
