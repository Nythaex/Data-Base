package com.example.bonusexa.services;

import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.printDtos.SuppliersPrintDto;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    void seed() throws IOException;

    List<SuppliersPrintDto> getAllSuppliersThatAreNotFromAbroad();
}
