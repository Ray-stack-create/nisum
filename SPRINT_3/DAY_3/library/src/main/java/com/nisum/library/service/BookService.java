package com.nisum.library.service;
import com.nisum.library.model.Book;
import com.nisum.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
        import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService {
    private static final List<Book> books = new ArrayList<>();
    private static int currentId = 1;

    public List<Book> getAllBooks(String author, Integer year, int page, int size) {
        Stream<Book> stream = books.stream();
        if (author != null) stream = stream.filter(b -> b.getAuthor().equalsIgnoreCase(author));
        if (year != null) stream = stream.filter(b -> b.getPublishedYear() == year);
        return stream.skip((page - 1) * size).limit(size).collect(Collectors.toList());
    }

    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));
    }

    public void addBook(Book book) {
        book.setId(currentId++);
        books.add(book);
    }

    public void updateBook(int id, Book book) {
        Book existing = getBookById(id);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublishedYear(book.getPublishedYear());
    }

    public void deleteBook(int id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}
