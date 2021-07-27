package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.printDtos.SuppliersPrintDto;
import com.example.bonusexa.models.dto.seedDtos.SeedSupplierDto;
import com.example.bonusexa.repos.SupplierRepository;
import com.example.bonusexa.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.bonusexa.constants.Constants.FILES_PATH;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName="suppliers.json";
    private final Validator validator;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seed() throws IOException {
        if (supplierRepository.count()>0){
            return;
        }
        String items = Files.readString(Path.of(FILES_PATH + fileName));
        SeedSupplierDto[] seedSupplierDtos = gson.fromJson(items, SeedSupplierDto[].class);
        Arrays.stream(seedSupplierDtos).filter(s->validator.validate(s).isEmpty()).forEach(s->{
            Supplier map = mapper.map(s, Supplier.class);
            supplierRepository.save(map);
        });

    }

    @Override
    public List<SuppliersPrintDto> getAllSuppliersThatAreNotFromAbroad() {
        return supplierRepository.findNativeSuppliers().stream().map(supplier -> {
            SuppliersPrintDto suppliersPrintDto=mapper.map(supplier,SuppliersPrintDto.class);
            suppliersPrintDto.setPartsCountDto(supplier.getParts().size());
            return  suppliersPrintDto;
        }).collect(Collectors.toList());
    }
}
