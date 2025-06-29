package com.nisum.book_api.service;

import com.nisum.book_api.dto.BookDTO;
import com.nisum.book_api.entity.Book;
import com.nisum.book_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public BookDTO create(BookDTO dto) {
        Book book = repo.save(new Book(null, dto.title(), dto.author(), dto.pages()));
        return toDTO(book);
    }

    public BookDTO get(Long id) {
        return repo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPages(dto.pages());
        return toDTO(repo.save(book));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<BookDTO> list() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    private BookDTO toDTO(Book book) {
        return new BookDTO(book.getTitle(), book.getAuthor(), book.getPages());
    }
}
