package com.kiit.book.controller;


import com.kiit.book.exception.BookNotFoundException;
import com.kiit.book.model.Book;
import com.kiit.book.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@Import(BookControllerTest.MockServiceConfig.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @TestConfiguration
    static class MockServiceConfig {
        @Bean
        public BookService bookService() {
            return Mockito.mock(BookService.class);
        }
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(bookService);
    }

    @Test
    void testGetBookById_ValidId_ReturnsBook() throws Exception {
        Book book = new Book(1L, "1984", "George Orwell");
        Mockito.when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(jsonPath("$.author").value("George Orwell"))
                .andDo(document("get-book-success",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("id").description("ID of the book"),
                                PayloadDocumentation.fieldWithPath("title").description("Title of the book"),
                                PayloadDocumentation.fieldWithPath("author").description("Author of the book")
                        )
                ));
    }


    @Test
    void testGetBookById_InvalidId_Returns404() throws Exception {
        Mockito.when(bookService.getBookById(anyLong())).thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(get("/api/books/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Book Not Found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("Book not found with id: 99"))
                .andExpect(jsonPath("$.instance").value("/api/books/99"));
    }


}
