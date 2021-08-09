package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.seedDtos.SeedPartDto;
import com.example.bonusexa.models.dto.seedDtos.SeedSupplierDto;
import com.example.bonusexa.repos.PartRepository;
import com.example.bonusexa.repos.SupplierRepository;
import com.example.bonusexa.services.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import static com.example.bonusexa.constants.Constants.FILES_PATH;

@Service
public class PartServiceImpl implements PartService {
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName="parts.json";
    private final Validator validator;

    public PartServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seed() throws IOException {
        if (partRepository.count()>0){
            return;
        }
        String items = Files.readString(Path.of(FILES_PATH + fileName));
        SeedPartDto[] seedPartDtos = gson.fromJson(items, SeedPartDto[].class);
        Arrays.stream(seedPartDtos).filter(s->validator.validate(s).isEmpty()).forEach(s->{
            Part part=mapper.map(s,Part.class);
            Long randomNum =Long.parseLong(String.valueOf(getRandomNumberUsingInts(1, Integer.parseInt(String.valueOf(supplierRepository.count())))));
            Supplier supplier = supplierRepository.findById(randomNum).orElse(null);

            part.setSupplier(supplier);
            partRepository.save(part);
        });
    }
    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
