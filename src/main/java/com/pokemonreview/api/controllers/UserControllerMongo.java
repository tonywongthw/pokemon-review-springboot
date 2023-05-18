package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.LoginDto;
import com.pokemonreview.api.models.UserEntityMongo;
import com.pokemonreview.api.repository.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserControllerMongo {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @PostMapping("loginMongo")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        Optional<UserEntityMongo> userEntity = userRepositoryMongo.findByUsername(loginDto.getUsername());

        // if no such user present
        if (!userEntity.isPresent()) {
            return new ResponseEntity<>("No such user", HttpStatus.BAD_REQUEST);
        // if password is incorrect
        } else if (!userEntity.get().getPassword().equals(loginDto.getPassword())){
            return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
        // if with that user and password is correct, return token
        } else {
            return new ResponseEntity<>("Login Succeed", HttpStatus.OK);
        }
    }

    @PostMapping("/registerMongo")
    public ResponseEntity<String> register(@RequestBody UserEntityMongo userEntity){
        try {

            if (userRepositoryMongo.existsByUsername(userEntity.getUsername())) {
                return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
            }

            UserEntityMongo itemToSave = new UserEntityMongo(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
            userRepositoryMongo.save(itemToSave);
            return new ResponseEntity<>("User registered success!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/users")
    public ResponseEntity<UserEntity> getUserByName() {
        try {

            UserEntity result = userRepository.findByUsername("test");

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        try {
            List<UserEntity> result = userRepository.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}