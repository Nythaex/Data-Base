package com.example.springintroexa.repositories;

import com.example.springintroexa.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
   Author getAuthorById(Long id);
   @Query("SELECT a FROM Author a  JOIN a.books b ORDER BY b.size Desc")
   List<Author> getAuthorsOrderByBooksCountDesc();


}
