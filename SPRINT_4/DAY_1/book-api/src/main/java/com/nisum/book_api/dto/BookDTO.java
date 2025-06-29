package com.nisum.book_api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BookDTO(
        @NotBlank String title,
        @NotBlank String author,
        @Positive int pages
) {}

