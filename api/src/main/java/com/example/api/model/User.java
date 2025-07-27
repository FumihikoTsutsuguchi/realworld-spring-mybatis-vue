package main.java.com.example.api.model;

import java.time.LocalDateTime;

public record User(
    Integer id,
    String username,
    String email,
    String password,
    LocalDateTime createdAt) {}