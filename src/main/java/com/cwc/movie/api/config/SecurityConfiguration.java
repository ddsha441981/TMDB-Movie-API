package com.cwc.movie.api.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cwc.movie.api.config.security.JwtAuthEntryPoint;
import com.cwc.movie.api.config.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	//Create JWTAuthFilter Bean
	@Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter();
    }
	//Create Password Encoder Bean
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return bCryptPasswordEncoder();
	}
	//Create SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//disable CORS
		httpSecurity.cors().disable();
		//disable CSRF
		httpSecurity.csrf().disable();
		//Session Management to STATELESS
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//Add Exception Handler
		httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint);
		//Authorize HTTP requests
		httpSecurity.authorizeHttpRequests()
		.requestMatchers("/api/v4/**").permitAll()
		.requestMatchers("/api/v4/auth/**").permitAll()
		.requestMatchers("/api/v4/user/**").permitAll()
		.anyRequest()
		.authenticated();
		 // Add JWT authentication filter
		httpSecurity.addFilterBefore((Filter) jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
		
	}

	
}
