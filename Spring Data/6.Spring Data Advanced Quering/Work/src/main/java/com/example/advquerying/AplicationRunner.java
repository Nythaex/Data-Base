package com.example.advquerying;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.IngredientsRepository;
import com.example.advquerying.repositories.ShampoosRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AplicationRunner implements ApplicationRunner {
    private final IngredientsRepository ingredientsRepository;
    private final ShampoosRepository shampoosRepository;

    public AplicationRunner(IngredientsRepository ingredientsRepository, ShampoosRepository shampoosRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.shampoosRepository = shampoosRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
//1         shampoosRepository.findAllBySizeOrderById(Size.MEDIUM).forEach(System.out::println);
//2          shampoosRepository.findAllBySizeOrLabel_IdOrderByPrice(Size.MEDIUM,10L).forEach(System.out::println);
//3          shampoosRepository.findAllByPriceAfterOrderByPriceDesc(BigDecimal.valueOf(5)).forEach(System.out::println);
//4        ingredientsRepository.findAllByNameStartingWith("M").forEach(System.out::println);
//5      String[] names=new String[]{"Lavender","Herbs","Apple"};
//        ingredientsRepository.findAllByNameIn(Arrays.asList(names)).forEach(System.out::println);
//6       System.out.println(shampoosRepository.countAllByPriceBefore(BigDecimal.valueOf(8.50)));
//7   shampoosRepository.findAllByIngredientsIn(Arrays.stream(new String[]{"Berry", "Mineral-Collagen"}).collect(Collectors.toList())).stream().map(Shampoo::getBrand).distinct().forEach(System.out::println);
        //8       shampoosRepository.findAllByIngredientsLessThan(2).forEach(System.out::println);
        //9     ingredientsRepository.deleteByName("Macadamia Oil");
        //10    ingredientsRepository.updateIngredientPriceBy10();
          ingredientsRepository.updateIngredientWithGivenNamePriceBy10(Arrays.stream(new String[]{"Mineral-Collagen","Zinc Pyrithione"}).collect(Collectors.toList()));


    }
}
