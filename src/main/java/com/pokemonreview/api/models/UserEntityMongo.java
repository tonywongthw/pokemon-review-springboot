package com.pokemonreview.api.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserEntityMongo {

    @Id
    private int id;

    private String username;

    private String password;
}