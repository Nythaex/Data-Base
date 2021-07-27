package com.example.springintroexa.services.impl;

import com.example.springintroexa.enums.AgeRestriction;
import com.example.springintroexa.enums.EditionType;
import com.example.springintroexa.models.Book;
import com.example.springintroexa.repositories.BookRepository;
import com.example.springintroexa.services.interfaces.AuthorService;
import com.example.springintroexa.services.interfaces.BookService;
import com.example.springintroexa.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
   private final CategoryService categoryService;
   private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }




    @Override
    public void seedBooks() {
        try {
            if (bookRepository.count()>0){
                return;
            }
            Files.readAllLines(Path.of("src/main/resources/files/books.txt")).forEach(row->{
                String [] tokens=row.split("\\s+");

                Book book=new Book();
                book.setAuthor(authorService.findRandomAuthor());
               Long count =(long) ((Math.random() * (categoryService.getCount() - 1)) + 1);
                for (int i = 0; i < count; i++) {
                    book.getCategories().add(categoryService.findRandomCategory());
                }
                book.setEditionType(EditionType.values()[Integer.parseInt(tokens[0])]);


                book.setReleaseDate(LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("d/M/yyyy")));

                book.setCopies(Long.parseLong(tokens[2]));
                book.setPrice(BigDecimal.valueOf(Double.parseDouble(tokens[3])));
                book.setAgeRestriction(AgeRestriction.values()[Integer.parseInt(tokens[4])]);
                book.setTitle(Arrays.stream(tokens).skip(5).collect(Collectors.joining(" ")));

                bookRepository.save(book);


            });

        } catch (IOException e) {
        }


    }

    @Override
    public List<Book> getAllBooksAfter(Long year) {
        List<Book> books=bookRepository.getBooksByReleaseDateAfter(LocalDate.of(Math.toIntExact(year),12,31));

        return books;
    }

    @Override
    public Set<String> getAllBooksBefore(int year) {
      return bookRepository.getBooksByReleaseDateBefore(LocalDate.of(year,1,1)).stream().map(s->String.format("%s %s",s.getAuthor().getFirstName(),s.getAuthor().getLastName())).collect(Collectors.toSet());

    }

    @Override
    public List<String> getAllGeorgePowellBooksOrdered() {
        return bookRepository.getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc("George","Powell").stream().map(s->s.getTitle()+" "+s.getReleaseDate()+" "+s.getCopies()).collect(Collectors.toList());
    }
}
