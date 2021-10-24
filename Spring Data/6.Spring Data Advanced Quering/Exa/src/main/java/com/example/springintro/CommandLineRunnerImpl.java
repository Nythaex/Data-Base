package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader reader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader reader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;

    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.print("Write exa:");
        String exa = reader.readLine();
        switch (exa) {
            case "1":
                System.out.print("Write restriction:");
                String restriction = reader.readLine();
                bookService.findAllBooksByAgeRestriction(restriction).forEach(s -> System.out.println(s.getTitle()));
                break;
            case "2":
                bookService.findAllBooksGoldenEditionWithLessThan5000Copies().forEach(s -> System.out.println(s.getTitle()));
                break;
            case "3":
                bookService.findAllBooksWithPriceLowerThan5AndHigherThan40().forEach(s -> System.out.println(s.getTitle() + " " + s.getPrice()));
                break;
            case "4":
                System.out.print("Write year:");
                int year = Integer.parseInt(reader.readLine());
                bookService.findAllBookThatAreNotInTheGivenYear(year).forEach(s -> System.out.println(s.getTitle()));
                break;
            case "5":
                System.out.print("Write date(dd-MM-yyyy):");
                String date = reader.readLine();
                bookService.findAllBeforeGivenDate(date).forEach(s -> System.out.println(s.getTitle() + " " + s.getEditionType() + " " + s.getPrice()));
                break;
            case "6":
                System.out.print("Write symbols:");
                String symbols = reader.readLine();
                authorService.getAllAuthorsWithFirstNameEndsWith(symbols).forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
                break;
            case "7":
                System.out.print("Write string:");
                String str = reader.readLine();
                bookService.findAllByStringContainsInTheTitle(str).forEach(s -> System.out.println(s.getTitle()));
                break;
            case "8":
                System.out.print("Author last name starts with:");
                String authorLastNameStartsWith = reader.readLine();
                bookService.findAllByAuthorLastNameStartsWith(authorLastNameStartsWith).forEach(s -> System.out.println(s.getTitle()));
                break;
            case "9":
                System.out.print("Title size:");
                int titleSize = Integer.parseInt(reader.readLine());
                int booksCount = bookService.findCountOfAllBooksWithTitleLongerThanTheGivenNumber(titleSize);
                System.out.println(String.format("There are %d books with longer title than %d symbols", booksCount, titleSize));

                break;

            case "10":
                authorService.findCountOfAllBooksCopiesByAuthor().forEach(s -> System.out.println(s[0] + " " + s[1] + " - " + s[2]));

                break;
            case "11":
                System.out.println("Write title:");
                String title = reader.readLine();
                bookService.findInfoAboutBooksWithGivenTitle(title).forEach(s -> System.out.println(s[0] + " " + s[1] + " " + s[2] + " " + s[3]));

                break;
            case "12":
                System.out.print("Print date:");
                LocalDate givenDate = LocalDate.parse(reader.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.print("Print books count:");
                int bookIncrease = Integer.parseInt(reader.readLine());
                Integer added = bookService.increasedBooksCount(givenDate, bookIncrease);
                System.out.printf("%d books are released after %s, so total of %d book copies were added",
                        added, bookIncrease, added * bookIncrease);


                break;
            case "13":
                System.out.print("Book copies:");
                int books = Integer.parseInt(reader.readLine());
                System.out.println(bookService.deleteBooksByCount(books));

                break;
            case "14":
//create procedure get_authors_books_by_author_name(
                //first_name_ VARCHAR(255), last_name_ VARCHAR(255), OUT result VARCHAR(255))
                //BEGIN
                //   set  result = (select concat_ws(' ', a.first_name, a.last_name,'has written', count(b.id), 'books')
                //    from authors as a
                //             join books b on a.id = b.author_id
                //    where a.first_name = first_name_
                //      and a.last_name = last_name_
                //    group by author_id);
                //
                //end;
                System.out.print("Name:");
                String[] name = reader.readLine().split(" ");

                System.out.println(bookService.findAllBooksByAuthor(name[0], name[1]));
                break;
        }


    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
