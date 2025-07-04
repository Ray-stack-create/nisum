package com.kiit.book.controller;
import com.kiit.book.exception.BookNotFoundException;
import com.kiit.book.model.Book;
import com.kiit.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return ResponseEntity.ok(book);
    }
}
