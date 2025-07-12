package com.example.library.util;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFactory {

    public Book fromDto(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublishedDate(dto.getPublishedDate());
        book.setIsbn(dto.getIsbn());
        return book;
    }
}

