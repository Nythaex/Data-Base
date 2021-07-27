package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampoosRepository extends JpaRepository<Shampoo,Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long id);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    int countAllByPriceBefore(BigDecimal price);

    @Query("SELECT DISTINCT s From Shampoo s join s.ingredients i WHERE i.name IN (:ingredients)")
    List<Shampoo> findAllByIngredientsIn(@Param("ingredients") List<String> ingredients);


   @Query("SELECT s From Shampoo s where s.ingredients.size <:ingredientsCount")
    List<Shampoo> findAllByIngredientsLessThan(@Param("ingredientsCount") int count);









}
