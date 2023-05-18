package com.pokemonreview.api.repository;


import com.pokemonreview.api.models.UserEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserEntityMongo, String> {

    Optional<UserEntityMongo> findByUsername(String username);
    Boolean existsByUsername(String username);
}