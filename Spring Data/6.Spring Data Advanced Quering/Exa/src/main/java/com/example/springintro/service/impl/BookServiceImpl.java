package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllBooksByAgeRestriction(String restriction) {
        AgeRestriction ageRestriction= AgeRestriction.valueOf(restriction.toUpperCase());
        return bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllBooksGoldenEditionWithLessThan5000Copies() {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000);
    }

    @Override
    public List<Book> findAllBooksWithPriceLowerThan5AndHigherThan40() {

        return bookRepository.findAllByPriceGreaterThanOrPriceLessThan(BigDecimal.valueOf(40),BigDecimal.valueOf(5));
    }

    @Override
    public List<Book> findAllBookThatAreNotInTheGivenYear(int year) {
        LocalDate before=LocalDate.of(year, Month.JANUARY,1);
        LocalDate after=LocalDate.of(year,12,31);

        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(before,after);
    }

    @Override
    public List<Book> findAllBeforeGivenDate(String date) {
        LocalDate localDate=LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return bookRepository.findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<Book> findAllByStringContainsInTheTitle(String str) {
        return bookRepository.findAllByTitleContaining(str);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartsWith(String authorLastNameStartsWith) {
        return bookRepository.findAllByAuthorLastNameStartsWith(authorLastNameStartsWith);
    }

    @Override
    public Integer findCountOfAllBooksWithTitleLongerThanTheGivenNumber(int titleSize) {
        return bookRepository.findAllByTitleSizeLongerThan(titleSize);
    }

    @Override
    public List<Object[]> findInfoAboutBooksWithGivenTitle(String title) {
        return bookRepository.findInfoAboutBooksWithGivenTitle(title);
    }

    @Override
    public Integer increasedBooksCount(LocalDate date, Integer count) {
        return bookRepository.addGivenBooksAfterThatDate(date,count);
    }

    @Override
    public Integer deleteBooksByCount(Integer count) {
        return bookRepository.deleteBook(count);
    }

    @Override
    public String findAllBooksByAuthor(String firstName,String lastName) {
        return bookRepository.getAllByAuthor(firstName,lastName);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
