package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.seed.xml.SeedOfferRoot;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private final String PATH="src/main/resources/files/xml/offers.xml";
    private final CarService carService;
    private final SellerService sellerService;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser parser;

    public OfferServiceImpl(CarService carService, SellerService sellerService, OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil,  XmlParser parser) {
        this.carService = carService;
        this.sellerService = sellerService;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.parser = parser;

    }

    @Override
    public boolean areImported() {
        return offerRepository.count()>0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        SeedOfferRoot seedOfferRoot = parser.fromFile(PATH, SeedOfferRoot.class);
        StringBuilder builder=new StringBuilder();
        seedOfferRoot.getOffersList().stream().filter(s1->{
            if (validationUtil.isValid(s1)){
                builder.append(String.format("Successfully import offer %S - ",s1.getAddedOn())+s1.getHasGoldStatus());
                builder.append(System.lineSeparator());
                return true;
            }else {
                builder.append(String.format("Invalid offer%n"));
                return false;
            }
        }).forEach(s1->{
            Offer map = modelMapper.map(s1, Offer.class);
            map.setCar(carService.getCarById(s1.getCar().getId()));
            map.setSeller(sellerService.getById(s1.getSeller().getId()));
            offerRepository.save(map);
        });

        return builder.toString().trim();
    }
}
