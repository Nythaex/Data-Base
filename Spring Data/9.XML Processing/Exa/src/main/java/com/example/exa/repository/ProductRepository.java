package com.example.exa.repository;

import com.example.exa.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("Select p FROM Product p Where p.price>=:greater AND p.price<=:lower AND p.buyer IS NULL ORDER BY p.price Asc")
    List<Product> findAllByPriceGreaterThanAndPriceLessThan(@Param("greater") BigDecimal greater, @Param("lower")BigDecimal lower);
}
