package com.cwc.movie.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cwc.movie.api.entities.MovieUser;

@Repository
public interface MovieUserRepository extends MongoRepository<MovieUser, String> {

	boolean existsByUsername(String username);

	Optional<MovieUser> findByUsername(String username);

}
