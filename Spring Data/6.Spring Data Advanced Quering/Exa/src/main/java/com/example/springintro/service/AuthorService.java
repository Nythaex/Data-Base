package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> getAllAuthorsWithFirstNameEndsWith(String symbols);

    List<Object[]> findCountOfAllBooksCopiesByAuthor();
}
