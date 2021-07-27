package com.example.springintroexa.services.interfaces;

import com.example.springintroexa.models.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface BookService {
    void seedBooks();
   List<Book> getAllBooksAfter(Long year);
   Set<String> getAllBooksBefore(int year);
   List<String> getAllGeorgePowellBooksOrdered();
}
