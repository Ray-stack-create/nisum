package com.example.library.service;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import java.util.List;


public interface BookService {
    Book createBook(BookDto bookDto);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);
}
