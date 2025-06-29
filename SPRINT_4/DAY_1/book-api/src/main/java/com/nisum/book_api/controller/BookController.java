package com.nisum.book_api.controller;
import com.nisum.book_api.dto.BookDTO;
import com.nisum.book_api.entity.Book;
import com.nisum.book_api.repository.BookRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Book API", description = "Operations on books")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> listBooks() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = new Book(null, bookDTO.title(), bookDTO.author(), bookDTO.pages());

        return ResponseEntity.ok(repository.save(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isEmpty()) return ResponseEntity.notFound().build();

        Book existing = optionalBook.get();
        existing.setTitle(bookDTO.title());
        existing.setAuthor(bookDTO.author());
        existing.setPages(bookDTO.pages());

        return ResponseEntity.ok(repository.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

