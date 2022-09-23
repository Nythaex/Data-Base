package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.seed.xml.SeedSellerRoot;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Rating;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class SellerServiceImpl implements SellerService {
    private final String PATH="src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser parser;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser parser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.parser = parser;
    }


    @Override
    public boolean areImported() {
        return sellerRepository.count()>0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
       return Files.readString(Path.of(PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        SeedSellerRoot root= parser.fromFile(PATH,SeedSellerRoot.class);
        StringBuilder builder=new StringBuilder();
        root.getSellers().stream().filter(s1->{
            if (validationUtil.isValid(s1)){
                builder.append(String.format("Successfully import seller %s - %s%n",s1.getLastName(),s1.getEmail()));
                return true;
            }else {
                builder.append(String.format("Invalid seller%n"));
                return false;
            }
        }).forEach(s1->{
                sellerRepository.save(modelMapper.map(s1, Seller.class));
        });

        return builder.toString().trim();
    }

    @Override
    public Seller getById(Long id) {
       return sellerRepository.findById(id).orElse(null);
    }
}
