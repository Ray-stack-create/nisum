package com.example.book_service.service;

import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAndFetchBook() {
        Book book = new Book(null, "Clean Code", "Robert Martin");
        Book saved = bookService.addBook(book);
        assertThat(saved.getId()).isNotNull();
        assertThat(bookService.getBookById(saved.getId()).getTitle()).isEqualTo("Clean Code");
    }
}
