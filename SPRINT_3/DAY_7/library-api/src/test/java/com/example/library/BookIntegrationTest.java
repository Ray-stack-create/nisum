package com.example.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long createdBookId;

    @Test
    @Order(1)
    void testCreateBook() throws Exception {
        Book book = new Book(null, "Effective Java", "Joshua Bloch", 2008);

        MvcResult result = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andReturn();

        Book created = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
        createdBookId = created.getId();
    }

    @Test
    @Order(2)
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(3)
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/books/{id}", createdBookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));
    }

    @Test
    @Order(4)
    void testUpdateBook() throws Exception {
        Book updated = new Book(null, "Effective Java 3rd Edition", "Joshua Bloch", 2018);

        mockMvc.perform(put("/books/{id}", createdBookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java 3rd Edition"))
                .andExpect(jsonPath("$.publishedYear").value(2018));
    }

    @Test
    @Order(5)
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", createdBookId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    void testGetDeletedBook() throws Exception {
        mockMvc.perform(get("/books/{id}", createdBookId))
                .andExpect(status().isNotFound());
    }
}
