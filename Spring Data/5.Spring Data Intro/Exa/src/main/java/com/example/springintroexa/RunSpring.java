package com.example.springintroexa;

import com.example.springintroexa.models.Author;
import com.example.springintroexa.models.Book;
import com.example.springintroexa.services.interfaces.AuthorService;
import com.example.springintroexa.services.interfaces.BookService;
import com.example.springintroexa.services.interfaces.CategoryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RunSpring implements ApplicationRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public RunSpring(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        authorService.seedAuthor();
        categoryService.seedCategory();
        bookService.seedBooks();

//        bookService.getAllBooksAfter(2000L).forEach(s-> System.out.println(s.getTitle()));
//        bookService.getAllBooksBefore(1990).forEach(System.out::println);
     authorService.AllAuthorsOrderByBooksCountDesc().forEach(s-> System.out.printf("%s %s %s%n",s.getFirstName(),s.getLastName(),s.getBooks().size()));
        // bookService.getAllGeorgePowellBooksOrdered().forEach(System.out::println);

    }
}
