package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.ex3.PrintLocalSuppliersRoot;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SupplierService {
    void seed() throws IOException, JAXBException;

    PrintLocalSuppliersRoot getAllSuppliersThatAreNotFromAbroad();
}
