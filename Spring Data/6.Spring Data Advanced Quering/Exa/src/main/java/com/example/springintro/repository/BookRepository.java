package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceGreaterThanOrPriceLessThan(BigDecimal price, BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDate, LocalDate releaseDate2);


    List<Book> findAllByTitleContaining(String title);

    List<Book> findAllByAuthorLastNameStartsWith(String author_lastName);

    @Query("SELECT COUNT(b) from Book b where LENGTH(b.title)>:size")
    int findAllByTitleSizeLongerThan(@Param("size") Integer size);

    @Query("Select b.title,b.editionType,b.ageRestriction,b.price FROM Book b where b.title=:title")
    List<Object[]> findInfoAboutBooksWithGivenTitle( String title);

}
