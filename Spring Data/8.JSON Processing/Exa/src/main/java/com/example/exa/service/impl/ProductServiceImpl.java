package com.example.exa.service.impl;

import com.example.exa.model.dto.ProductWithSellerDto;
import com.example.exa.model.dto.SeedProductDto;
import com.example.exa.model.entity.Category;
import com.example.exa.model.entity.Product;
import com.example.exa.model.entity.User;
import com.example.exa.repository.CategoryRepository;
import com.example.exa.repository.ProductRepository;
import com.example.exa.repository.UserRepository;
import com.example.exa.service.interf.ProductService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.constant.AppConstants.FILES_PACKAGE_PATH;

@Service
public class ProductServiceImpl implements ProductService {
    private final String FILE_NAME = "products.json";
    private final Gson gson;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public ProductServiceImpl(Gson gson, ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper mapper, Validator validator) {
        this.gson = gson;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }


    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }
        String infos = Files.readString(Path.of(FILES_PACKAGE_PATH + FILE_NAME));
        SeedProductDto[] seedProductDtos = gson.fromJson(infos, SeedProductDto[].class);
        Arrays.stream(seedProductDtos).filter(s->validator.validate(s).isEmpty()).map(s -> mapper.map(s, Product.class)).forEach(s -> {
            long l = Long.parseLong(String.valueOf(getRandomNumberUsingNextInt(1, Integer.parseInt(String.valueOf(userRepository.count() - 1)))));
            User byId = userRepository.findById(l).orElse(null);
            s.setSeller(byId);


            if (getRandomNumberUsingNextInt(0, 2) != 1) {
                s.setBuyer(userRepository.findById(Long.parseLong(String.valueOf(getRandomNumberUsingNextInt(1, Integer.parseInt(String.valueOf(userRepository.count() - 1)))))).orElse(null));
            }
            for (int i = 0; i < 3; i++) {
                Category category = categoryRepository.findById(Long.parseLong(String.valueOf(getRandomNumberUsingNextInt(1, Integer.parseInt(String.valueOf(categoryRepository.count() - 1)))))).orElse(null);

                boolean check = false;
                for (Category c : s.getCategories()) {
                    if (c.getName().equals(category.getName())) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    s.getCategories().add(category);
                }

            }

            productRepository.save(s);
        });

    }

    @Override
    public List<ProductWithSellerDto> findByRange() {
        List<Product> allByPriceGreaterThanAndPriceLessThan = productRepository.findAllByPriceGreaterThanAndPriceLessThan(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        List<ProductWithSellerDto> collect = allByPriceGreaterThanAndPriceLessThan.stream().map(s -> {
            ProductWithSellerDto map = mapper.map(s, ProductWithSellerDto.class);
            map.setSeller(s.getSeller().getFirstName() + " " + s.getSeller().getLastName());
            return map;
        }).collect(Collectors.toList());

        return collect;
    }

    private Integer getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;

    }
}
