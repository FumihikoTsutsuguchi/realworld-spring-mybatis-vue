package com.example.api.model;

import java.time.LocalDateTime;

public record User(
    Integer id,
    String username,
    String email,
    String password,
    LocalDateTime createdAt) {
        public User withId(Integer newId) {
            return new User(newId, username, email, password, createdAt);
        }
    }