package com.kiit.book.model;
import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;

    // Constructors, getters, setters

    public Book() {}

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

}
