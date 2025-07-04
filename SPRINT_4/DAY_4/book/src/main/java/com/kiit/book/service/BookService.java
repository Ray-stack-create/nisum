package com.kiit.book.service;
import com.kiit.book.model.Book;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookById(Long id);
}

