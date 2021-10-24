package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.seedDtos.SeedPartDto;
import com.example.bonusexa.models.dto.seedDtos.SeedPartsRoot;
import com.example.bonusexa.repos.PartRepository;
import com.example.bonusexa.repos.SupplierRepository;
import com.example.bonusexa.services.PartService;
import com.example.bonusexa.ustils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;



@Service
public class PartServiceImpl implements PartService {
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName="parts.json";
    private final Validator validator;
    public  String PATH="src/main/resources/09. XML-Processing-Exercises/";
    private String FILE = "parts.xml";
    private final XmlParser xmlParser;

    public PartServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, Gson gson, ModelMapper mapper, Validator validator, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seed() throws IOException, JAXBException {
        if (partRepository.count()>0){
            return;
        }
        SeedPartsRoot seedPartsRoot = xmlParser.fromFile(PATH + FILE, SeedPartsRoot.class);

        seedPartsRoot.getParts().stream().filter(s->validator.validate(s).isEmpty()).forEach(s->{
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
