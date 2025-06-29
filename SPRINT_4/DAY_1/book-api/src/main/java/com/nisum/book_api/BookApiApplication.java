package com.nisum.book_api;

import com.nisum.book_api.entity.Book;
import com.nisum.book_api.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}
	@Bean
	CommandLineRunner init(BookRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				Book sample = new Book(null, "Sample Book", "John Doe", 123);
				repo.save(sample);
			}
		};
	}
}
