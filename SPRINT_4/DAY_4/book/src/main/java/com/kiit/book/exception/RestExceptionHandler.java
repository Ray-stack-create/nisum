package com.kiit.book.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(BookNotFoundException ex) {
        Map<String, Object> body = Map.of(
                "type", "https://example.com/problem/book-not-found",
                "title", "Book Not Found",
                "status", 404,
                "detail", ex.getMessage(),
                "instance", "/api/books/" + ex.getBookId()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}

