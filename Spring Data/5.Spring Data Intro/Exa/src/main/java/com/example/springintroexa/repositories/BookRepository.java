package com.example.springintroexa.repositories;

import com.example.springintroexa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> getBooksByReleaseDateBefore(LocalDate releaseDate);

    //@Query("select b from Book b wHERE b.author.firstName='George' AND b.author.lastName='Powell' ORDER BY b.releaseDate DESC,b.title")
  //  List<Book> getAllGeorgePowellBooks();
    List<Book> getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String author_firstName, String author_lastName);
}
