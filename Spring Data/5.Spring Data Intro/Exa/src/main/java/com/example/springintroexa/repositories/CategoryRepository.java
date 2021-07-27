package com.example.springintroexa.repositories;

import com.example.springintroexa.models.Author;
import com.example.springintroexa.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category getCategoryById(Long id);

}
