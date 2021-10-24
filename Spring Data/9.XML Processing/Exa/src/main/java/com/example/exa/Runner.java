package com.example.exa;

import com.example.exa.model.dto.ex1.PrintAllProductsInRange;
import com.example.exa.model.dto.ex2.PrintAllUsersAndPorductsRoot;
import com.example.exa.model.dto.ex3.PrintAllCategoriesInfoRoot;
import com.example.exa.model.dto.ex4.AllUserWitSoldItemsDto;
import com.example.exa.model.dto.ex4.PrintEverything;
import com.example.exa.service.interf.CategoryService;
import com.example.exa.service.interf.ProductService;
import com.example.exa.service.interf.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

@Component
public class Runner implements ApplicationRunner {
    private  BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
    private CategoryService categoryService;
     private UserService userService;
     private ProductService productService;
      private final String PRINT_FIELD="src/main/resources/09. XML-Processing-Exercises/out/";

    public Runner(CategoryService categoryService, UserService userService, ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(PrintAllProductsInRange.class,
                PrintAllUsersAndPorductsRoot.class, PrintAllCategoriesInfoRoot.class, PrintEverything.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        seedData();
        System.out.print("Ex:");
        int read = Integer.parseInt(reader.readLine());
        switch (read) {
            case 1:
                marshaller.marshal(productService.findByRange(), new FileWriter(PRINT_FIELD + "firstExa.xml"));

                break;
            case 2:
               marshaller.marshal(userService.getAllUsersWithAtLeastOneProductSold(),new File(PRINT_FIELD+"secondExa.xml"));
                break;
            case 3:
                marshaller.marshal(categoryService.getAllCategories(),new File(PRINT_FIELD+"thirdExa.xml"));
                break;
            case 4:
              marshaller.marshal(userService.getAllUsersWithAtLeastOneProductSoldByCount(),new File(PRINT_FIELD+"fourExa.xml"));
                break;

        }

    }

    private void seedData() {
        try {
            categoryService.seedCategories();
            userService.seedUsers();
            productService.seedProducts();


        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}

