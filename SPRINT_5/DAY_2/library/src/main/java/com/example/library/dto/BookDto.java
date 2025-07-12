package com.example.library.dto;
import lombok.Data;
import java.time.LocalDate;


@Data
public class BookDto {
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String isbn;

    // Getters and Setters
}

