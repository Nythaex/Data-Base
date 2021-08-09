package com.example.exa.service.impl;

import com.example.exa.model.dto.*;
import com.example.exa.model.entity.Product;
import com.example.exa.model.entity.User;
import com.example.exa.repository.UserRepository;
import com.example.exa.service.interf.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final String FILE_NAME = "users.xml";
    private final String FILES_PACKAGE_PATH="src/main/resources/09. XML-Processing-Exercises/";

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public UserServiceImpl( UserRepository userRepository, ModelMapper mapper, Validator validator) {

        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validator = validator;

    }

    @Override
    public void seedUsers() throws IOException, JAXBException {



        if (userRepository.count() == 0) {

            JAXBContext jaxbContext=JAXBContext.newInstance(SeedUserCollection.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SeedUserCollection seedUserDtoStream =(SeedUserCollection) unmarshaller.unmarshal(new FileReader(FILES_PACKAGE_PATH+FILE_NAME));
            seedUserDtoStream.getUsers().stream().filter(s->validator.validate(s).isEmpty()).forEach(dtoUser->{
                User map = mapper.map(dtoUser, User.class);
                userRepository.save(map);
            });


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
