package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Car;
import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.dto.printDtos.ex4.CarWithPartsDto;
import com.example.bonusexa.models.dto.printDtos.ex2.CarDto;
import com.example.bonusexa.models.dto.printDtos.ex2.PrintCarsFromMakeRoot;
import com.example.bonusexa.models.dto.printDtos.ex4.PrintAllCarsWithParts;
import com.example.bonusexa.models.dto.seedDtos.SeedCarsRoot;
import com.example.bonusexa.repos.CarRepository;
import com.example.bonusexa.repos.PartRepository;
import com.example.bonusexa.services.CarService;
import com.example.bonusexa.ustils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName = "cars.json";
    private final Validator validator;
    private final XmlParser xmlParser;
    private String FILE = "cars.xml";
    public  String PATH="src/main/resources/09. XML-Processing-Exercises/";

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper mapper, Validator validator, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seed() throws IOException, JAXBException {
        if (carRepository.count() > 0) {
            return;
        }
        SeedCarsRoot seedCarsRoot = xmlParser.fromFile( PATH+ FILE, SeedCarsRoot.class);

        seedCarsRoot.getCars().stream().filter(s -> validator.validate(s).isEmpty()).forEach(s -> {
            Car car = mapper.map(s, Car.class);
            Set<Part> parts = new HashSet<>();

            int partsCount = getRandomNumberUsingInts(3, 5);
            while (parts.size() != partsCount) {
                Long randomNum = Long.parseLong(String.valueOf(getRandomNumberUsingInts(1, Integer.parseInt(String.valueOf(partRepository.count())))));
                Part part = partRepository.findById(randomNum).orElse(null);

                parts.add(part);
            }


            car.setParts(parts);
            carRepository.save(car);
        });
    }

    @Override
    public PrintCarsFromMakeRoot getAllCarsByMake(String make) {
        List<CarDto> collect = carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make).stream().map(car -> {
            CarDto carDto = mapper.map(car, CarDto.class);
            return carDto;
        }).collect(Collectors.toList());

        return new PrintCarsFromMakeRoot(collect);
    }

    @Override
    public PrintAllCarsWithParts getAllCars() {
        List<CarWithPartsDto> collect = carRepository.findAll().stream().map(car -> {
            CarWithPartsDto carDto = mapper.map(car, CarWithPartsDto.class);
//
            return carDto;
        }).collect(Collectors.toList());
        return new PrintAllCarsWithParts(collect);
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
