package com.example.book_service.repository;

import com.example.book_service.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldFindBookByTitle() {
        bookRepository.save(new Book(null, "Domain Driven Design", "Eric Evans"));
        List<Book> result = bookRepository.findByTitle("Domain Driven Design");
        assertThat(result).isNotEmpty();
    }
}

