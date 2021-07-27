package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.kotlin.CoroutineCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientsRepository extends BaseRepository<Ingredient> {
    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameIn(Collection<String> name);

    @Query("DELETE FROM Ingredient i where i.name=:name")
     @Modifying
    void deleteByName(@Param("name") String name);

    @Query("UPDATE  Ingredient i SET i.price=i.price*1.1 ")
    @Modifying
    void updateIngredientPriceBy10();

    @Query("UPDATE  Ingredient i SET i.price=i.price*1.1 where i.name IN (:names)")
    @Modifying
    void updateIngredientWithGivenNamePriceBy10(@Param("names") List<String> names);


}
