package com.example.exa.service.impl;

import com.example.exa.model.dto.ProductWithSellerDto;
import com.example.exa.model.dto.SeedAllProductsDto;
import com.example.exa.model.dto.SeedProductDto;
import com.example.exa.model.entity.Category;
import com.example.exa.model.entity.Product;
import com.example.exa.model.entity.User;
import com.example.exa.repository.CategoryRepository;
import com.example.exa.repository.ProductRepository;
import com.example.exa.repository.UserRepository;
import com.example.exa.service.interf.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private final String FILE_NAME = "products.xml";
    private final String FILES_PACKAGE_PATH="src/main/resources/09. XML-Processing-Exercises/";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public ProductServiceImpl( ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper mapper, Validator validator) {

        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }


    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (productRepository.count() > 0) {
            return;
        }
        JAXBContext jaxbContext=JAXBContext.newInstance(SeedAllProductsDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

       SeedAllProductsDto seedAll =(SeedAllProductsDto) unmarshaller.unmarshal(new FileReader(FILES_PACKAGE_PATH + FILE_NAME));
        seedAll.getProducts().stream().filter(s->validator.validate(s).isEmpty()).map(s -> mapper.map(s, Product.class)).forEach(s -> {
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
