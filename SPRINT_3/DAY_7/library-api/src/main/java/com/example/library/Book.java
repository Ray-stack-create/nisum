package com.example.library;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int publishedYear;

    public Book() {}

    public Book(Long id, String title, String author, int publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishedYear() { return publishedYear; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublishedYear(int publishedYear) { this.publishedYear = publishedYear; }
}
