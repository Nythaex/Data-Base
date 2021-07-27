package com.example.springintroexa.services.impl;

import com.example.springintroexa.models.Category;
import com.example.springintroexa.repositories.CategoryRepository;
import com.example.springintroexa.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategory() {
        try {
            if (categoryRepository.count()>0){
                return;
            }
            Files.readAllLines(Path.of("src/main/resources/files/categories.txt")).forEach(row->{
                if (row.length()>0){
                    Category category=new Category(row);
                    categoryRepository.save(category);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Category findRandomCategory() {
        Long id=1 + (long) (Math.random() * (categoryRepository.count()- 1));
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Long getCount() {
        return categoryRepository.count();
    }
}
