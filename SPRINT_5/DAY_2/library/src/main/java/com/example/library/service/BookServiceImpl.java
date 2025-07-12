package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.util.BookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookFactory bookFactory;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookFactory bookFactory) {
        this.bookRepository = bookRepository;
        this.bookFactory = bookFactory;
    }

    @Override
    public Book createBook(BookDto bookDto) {
        return bookRepository.save(bookFactory.fromDto(bookDto));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID " + id));
    }

    @Override
    public Book updateBook(Long id, BookDto bookDto) {
        Book existing = getBookById(id);
        existing.setTitle(bookDto.getTitle());
        existing.setAuthor(bookDto.getAuthor());
        existing.setPublishedDate(bookDto.getPublishedDate());
        existing.setIsbn(bookDto.getIsbn());
        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
    }
}

