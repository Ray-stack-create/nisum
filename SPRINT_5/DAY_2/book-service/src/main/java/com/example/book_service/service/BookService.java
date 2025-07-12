package com.example.book_service.service;

import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
