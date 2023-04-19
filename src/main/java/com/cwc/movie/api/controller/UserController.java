package com.cwc.movie.api.controller;

import com.cwc.movie.api.dto.ApiResponse;
import com.cwc.movie.api.dto.MovieUserDto;
import com.cwc.movie.api.services.MovieUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/user")
public class UserController {

    @Autowired
    private MovieUserServices movieUserServices;

    @PutMapping("/{userId}")
    public ResponseEntity<MovieUserDto> updateUser(@RequestBody MovieUserDto movieUserDto , @PathVariable("userId") String userId){
        MovieUserDto updatedUser = this.movieUserServices.updateUser(movieUserDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MovieUserDto> findSingleUser(@PathVariable("userId") String userId){
        MovieUserDto retrivedUser = this.movieUserServices.getUserById(userId);
        return ResponseEntity.ok(retrivedUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieUserDto>> findAllUser(){
        List<MovieUserDto> allUsers = this.movieUserServices.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    //ADMIN
    // DELETE -delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId){
       this.movieUserServices.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }
}
