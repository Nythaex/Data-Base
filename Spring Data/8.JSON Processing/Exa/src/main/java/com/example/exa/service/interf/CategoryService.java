package com.example.exa.service.interf;

import com.example.exa.model.dto.CategoryDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;

    List<CategoryDto> getAllCategories();
}
