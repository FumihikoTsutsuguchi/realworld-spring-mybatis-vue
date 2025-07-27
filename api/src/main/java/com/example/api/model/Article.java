package main.java.com.example.api.model;

import java.time.LocalDateTime;

public record Article(
    Integer id,
    String slug,
    String title,
    String body,
    Integer authorId,
    LocalDateTime createdAt) {}