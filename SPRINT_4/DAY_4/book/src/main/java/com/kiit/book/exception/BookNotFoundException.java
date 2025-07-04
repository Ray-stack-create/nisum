package com.kiit.book.exception;


public class BookNotFoundException extends RuntimeException {
    private final Long bookId;

    public BookNotFoundException(Long id) {
        super("Book not found with id: " + id);
        this.bookId = id;
    }

    public Long getBookId() {
        return bookId;
    }
}

