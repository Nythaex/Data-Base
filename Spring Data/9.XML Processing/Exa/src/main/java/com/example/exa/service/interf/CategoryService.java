package com.example.exa.service.interf;

import com.example.exa.model.dto.ex3.CategoryDto;
import com.example.exa.model.dto.ex3.PrintAllCategoriesInfoRoot;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface CategoryService {
    public void seedCategories() throws JAXBException, FileNotFoundException;
    public PrintAllCategoriesInfoRoot getAllCategories();
}
