package com.pokemonreview.api.security;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 1800000; // 30 minutes in milliseconds
    public static final String JWT_SECRET = "secret"; // values to be changed in production env
}
