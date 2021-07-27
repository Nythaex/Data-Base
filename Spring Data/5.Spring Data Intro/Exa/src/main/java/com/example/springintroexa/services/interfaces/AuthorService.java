package com.example.springintroexa.services.interfaces;

import com.example.springintroexa.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface AuthorService {
    void seedAuthor();
    Author findRandomAuthor();
    List<Author> AllAuthorsOrderByBooksCountDesc();
}
