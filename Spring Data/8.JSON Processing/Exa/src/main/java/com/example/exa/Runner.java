package com.example.exa;

import com.example.exa.model.dto.AllUserWitSoldItemsDto;
import com.example.exa.service.interf.CategoryService;
import com.example.exa.service.interf.ProductService;
import com.example.exa.service.interf.UserService;
import com.google.gson.Gson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Runner implements ApplicationRunner {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final Gson gson;
    private final  BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

    public Runner(CategoryService categoryService, ProductService productService, UserService userService, Gson gson) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.gson = gson;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        seedData();
        System.out.print("Ex:");
        int read=Integer.parseInt(reader.readLine());
        switch (read){
            case 1:
                System.out.println(gson.toJson(productService.findByRange()));
                break;
            case 2:
                String s = gson.toJson(userService.getAllUsersWithAtLeastOneProductSold());
                System.out.println(s);
                break;
            case 3:
                System.out.println(gson.toJson(categoryService.getAllCategories()));
                break;
            case 4:
                AllUserWitSoldItemsDto allUsersWithAtLeastOneProductSoldByCount = userService.getAllUsersWithAtLeastOneProductSoldByCount();
                System.out.println(gson.toJson(allUsersWithAtLeastOneProductSoldByCount));
                break;

        }

    }

    private void seedData()  {
        try {
            categoryService.seedCategories();
            userService.seedUsers();
            productService.seedProducts();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
