package com.nisum.library.controller;
import com.nisum.library.model.Book;
import com.nisum.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) Integer publishedYear,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        return bookService.getAllBooks(author, publishedYear, page, size);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public String addBook(@Valid @RequestBody Book book) {
        bookService.addBook(book);
        return "Book added.";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        bookService.updateBook(id, book);
        return "Book updated.";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted.";
    }
}
