package com.example.exa.service.impl;

import com.example.exa.model.dto.*;
import com.example.exa.model.entity.Product;
import com.example.exa.model.entity.User;
import com.example.exa.repository.UserRepository;
import com.example.exa.service.interf.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.constant.AppConstants.FILES_PACKAGE_PATH;

@Service
public class UserServiceImpl implements UserService {
    private final String FILE_NAME = "users.json";
    private final Gson gson;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public UserServiceImpl(Gson gson, UserRepository userRepository, ModelMapper mapper, Validator validator) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() == 0) {
            SeedUserDto[] seedUserDtoStream = gson.fromJson(Files.readString(Path.of(FILES_PACKAGE_PATH + FILE_NAME)), SeedUserDto[].class);


            User[] userStream = mapper.map(seedUserDtoStream, User[].class);
            Arrays.stream(userStream).filter(s -> validator.validate(s).isEmpty()).forEach(userRepository::save);
        }


    }

    @Override
    public List<UserWithProductsDto> getAllUsersWithAtLeastOneProductSold() {
        List<UserWithProductsDto> users = userRepository.usersWhoHaveAtLeastItemSold().stream().map(s -> {
            UserWithProductsDto userWithProductsDtor = mapper.map(s, UserWithProductsDto.class);
            return userWithProductsDtor;
        }).collect(Collectors.toList());

        return users;
    }

    @Override
    public AllUserWitSoldItemsDto getAllUsersWithAtLeastOneProductSoldByCount() {
        List<User> users = new ArrayList<>(userRepository.usersWhoHaveAtLeastItemSoldByCount());
        AllUserWitSoldItemsDto allUserWitSoldItemsDto = new AllUserWitSoldItemsDto();

        List<UserWithAgeAndProductsDto> collect = users.stream().map(user -> {
            UserWithAgeAndProductsDto userWithAgeAndProductsDto = mapper.map(user, UserWithAgeAndProductsDto.class);
            ProductCountDto productCountDtos=new ProductCountDto();

            List<ProductNameAndPrice> collect1 = Arrays.stream(mapper.map(user.getProductsSold(), ProductNameAndPrice[].class)).collect(Collectors.toList());
            productCountDtos.setProducts(collect1);
            productCountDtos.setProductCount(collect1.size());

            userWithAgeAndProductsDto.setProductCountDtos(productCountDtos);
            return userWithAgeAndProductsDto;
        }).collect(Collectors.toList());


        allUserWitSoldItemsDto.setUsers(collect);
        allUserWitSoldItemsDto.setCount(allUserWitSoldItemsDto.getUsers().size());

        return allUserWitSoldItemsDto;
    }

//    List<User> users = this.userRepository.findAllUsersWithSoldProductsOrderedByNumberOfProductsDescAndLastName();
//    UsersCountDto usersCountDto = new UsersCountDto();

//    List<UsersAndProductsDto> usersAndProductsDtos = users.stream().map(user -> {

//                UsersAndProductsDto userDto = modelMapper.map(user, UsersAndProductsDto.class);
//                List<ProductNameAndPriceDto> productDtos = user.getSoldProducts().stream().map(product -> modelMapper.map(product, ProductNameAndPriceDto.class)).collect(Collectors.toList());
//                Products products = new Products();
//                products.setProducts(productDtos);
//                products.setCount(productDtos.size());
//                userDto.setListOfSoldProducts(products);
//
//                return userDto;
//            }

//    ).collect(Collectors.toList());
//        usersCountDto.setUsers(usersAndProductsDtos);
//        usersCountDto.setUsersCount(users.size());
//        return usersCountDto;
}
