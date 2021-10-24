package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.printDtos.ex3.SuppliersPrintDto;
import com.example.bonusexa.models.dto.printDtos.ex3.PrintLocalSuppliersRoot;
import com.example.bonusexa.models.dto.seedDtos.SeedSuppliersRoot;
import com.example.bonusexa.repos.SupplierRepository;
import com.example.bonusexa.services.SupplierService;
import com.example.bonusexa.ustils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName="suppliers.json";
    private final Validator validator;
    public  String PATH="src/main/resources/09. XML-Processing-Exercises/";
    private String FILE = "suppliers.xml";
    private final XmlParser xmlParser;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper mapper, Validator validator, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seed() throws IOException, JAXBException {
        if (supplierRepository.count()>0){
            return;
        }
        SeedSuppliersRoot seedSuppliersRoot = xmlParser.fromFile(PATH + FILE, SeedSuppliersRoot.class);

        seedSuppliersRoot.getSuppliers().stream().filter(s->validator.validate(s).isEmpty()).forEach(s->{
            Supplier map = mapper.map(s, Supplier.class);
            supplierRepository.save(map);
        });

    }

    @Override
    public PrintLocalSuppliersRoot getAllSuppliersThatAreNotFromAbroad() {
        List<SuppliersPrintDto> collect = supplierRepository.findNativeSuppliers().stream().map(supplier -> {
            SuppliersPrintDto suppliersPrintDto = mapper.map(supplier, SuppliersPrintDto.class);
            suppliersPrintDto.setPartsCountDto(supplier.getParts().size());
            return suppliersPrintDto;
        }).collect(Collectors.toList());

        return new PrintLocalSuppliersRoot(collect);
    }
}
