package com.example.exa.service.interf;

import com.example.exa.model.dto.ProductWithSellerDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

   List<ProductWithSellerDto> findByRange();
}
