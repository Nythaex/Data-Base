package com.example.exa.service.interf;

import com.example.exa.model.dto.ex1.PrintAllProductsInRange;
import com.example.exa.model.dto.ex1.ProductWithSellerDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void seedProducts() throws IOException, JAXBException;
    public PrintAllProductsInRange findByRange();
}
