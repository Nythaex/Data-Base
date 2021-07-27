package com.example.exa.service.impl;

import com.example.exa.model.dto.CategoryDto;
import com.example.exa.model.dto.SeedCategoryDto;
import com.example.exa.model.entity.Category;
import com.example.exa.model.entity.Product;
import com.example.exa.repository.CategoryRepository;
import com.example.exa.service.interf.CategoryService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.constant.AppConstants.FILES_PACKAGE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final static String FILE_NAME="categories.json";
    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public CategoryServiceImpl(Gson gson, CategoryRepository categoryRepository, ModelMapper mapper, Validator validator) {
        this.gson = gson;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count()>0){
            return;
        }
        String infos = Files.readString(Path.of(FILES_PACKAGE_PATH + FILE_NAME));
        SeedCategoryDto[] seedCategoryDtos = gson.fromJson(infos, SeedCategoryDto[].class);
        Arrays.stream(seedCategoryDtos).filter(s->validator.validate(s).isEmpty()).map(s->mapper.map(s, Category.class)).forEach(categoryRepository::save);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> list=categoryRepository.findAll().stream().map(category -> {
            CategoryDto categoryDto=mapper.map(category,CategoryDto.class);
            BigDecimal total=BigDecimal.valueOf(0);
            for (Product p:category.getProducts()){
                total=total.add(p.getPrice());
            }
            if (category.getProducts().size()!=0){
                categoryDto.setTotalRevenue(total);
                categoryDto.setProductCount(category.getProducts().size());
                categoryDto.setAveragePrice(total.divide(BigDecimal.valueOf(category.getProducts().size()), RoundingMode.valueOf(2)));
            }

            return categoryDto;
        }).collect(Collectors.toList());
        return list;
    }

}
