package com.example.springintroexa.services.impl;

import com.example.springintroexa.models.Author;
import com.example.springintroexa.repositories.AuthorRepository;
import com.example.springintroexa.services.interfaces.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthor() {
        try {
            if (authorRepository.count()>0){
                return;
            }
            Files.readAllLines(Path.of("src/main/resources/files/authors.txt")).forEach(row-> {
                String[] s = row.split(" ");
                Author author=new Author(s[0],s[1]);
                authorRepository.save(author);

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Author findRandomAuthor() {
        Long id=1 + (long) (Math.random() * (authorRepository.count()- 1));
        Author author= authorRepository.getAuthorById(id);
        return author;
    }

    @Override
    public List<Author> AllAuthorsOrderByBooksCountDesc() {
        List<Author> authorsOrderByBooksCountDesc = authorRepository.getAuthorsOrderByBooksCountDesc();
        return authorRepository.getAuthorsOrderByBooksCountDesc();
    }


}
