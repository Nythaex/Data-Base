package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllBooksByAgeRestriction(String restriction);

    List<Book> findAllBooksGoldenEditionWithLessThan5000Copies();

    List<Book> findAllBooksWithPriceLowerThan5AndHigherThan40();

    List<Book> findAllBookThatAreNotInTheGivenYear(int year);

    List<Book> findAllBeforeGivenDate(String date);

    List<Book> findAllByStringContainsInTheTitle(String str);

    List<Book> findAllByAuthorLastNameStartsWith(String authorLastNameStartsWith);

    Integer findCountOfAllBooksWithTitleLongerThanTheGivenNumber(int titleSize);

    List<Object[]> findInfoAboutBooksWithGivenTitle(String title);

    Integer increasedBooksCount(LocalDate date,Integer count);

    Integer deleteBooksByCount(Integer count);


   String findAllBooksByAuthor(String firstName,String lastName);
}
