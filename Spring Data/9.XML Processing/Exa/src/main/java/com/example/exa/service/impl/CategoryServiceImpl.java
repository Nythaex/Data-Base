package com.example.exa.service.impl;

import com.example.exa.model.dto.CategoryDto;
import com.example.exa.model.dto.SeedAllCategoriesDto;
import com.example.exa.model.dto.SeedAllProductsDto;
import com.example.exa.model.dto.SeedCategoryDto;
import com.example.exa.model.entity.Category;
import com.example.exa.model.entity.Product;
import com.example.exa.repository.CategoryRepository;
import com.example.exa.service.interf.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final static String FILE_NAME = "categories.xml";
    private final String FILES_PACKAGE_PATH = "src/main/resources/09. XML-Processing-Exercises/";

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper, Validator validator) {

        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (categoryRepository.count() > 0) {
            return;
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(SeedAllCategoriesDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        SeedAllCategoriesDto seedAll = (SeedAllCategoriesDto) unmarshaller.unmarshal(new FileReader(FILES_PACKAGE_PATH + FILE_NAME));
        List<Category> collect = seedAll.getCategoryes().stream().filter(s -> validator.validate(s).isEmpty()).map(s -> mapper.map(s, Category.class)).collect(Collectors.toList());
        collect.forEach(categoryRepository::save);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> list = categoryRepository.findAll().stream().map(category -> {
            CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
            BigDecimal total = BigDecimal.valueOf(0);
            for (Product p : category.getProducts()) {
                total = total.add(p.getPrice());
            }
            if (category.getProducts().size() != 0) {
                categoryDto.setTotalRevenue(total);
                categoryDto.setProductCount(category.getProducts().size());
                categoryDto.setAveragePrice(total.divide(BigDecimal.valueOf(category.getProducts().size()), RoundingMode.valueOf(2)));
            }

            return categoryDto;
        }).collect(Collectors.toList());
        return list;
    }

}
