package com.example.springintroexa.services.interfaces;

import com.example.springintroexa.models.Category;
import com.example.springintroexa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    void seedCategory();
    Category findRandomCategory();
    Long getCount();
}
