package com.example.bonusexa.services.impl;

import com.example.bonusexa.models.Car;
import com.example.bonusexa.models.Part;
import com.example.bonusexa.models.Supplier;
import com.example.bonusexa.models.dto.printDtos.CarWithPartsDto;
import com.example.bonusexa.models.dto.printDtos.PartDto;
import com.example.bonusexa.models.dto.seedDtos.CarDto;
import com.example.bonusexa.models.dto.seedDtos.SeedCarDto;
import com.example.bonusexa.models.dto.seedDtos.SeedPartDto;
import com.example.bonusexa.repos.CarRepository;
import com.example.bonusexa.repos.PartRepository;
import com.example.bonusexa.services.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.bonusexa.constants.Constants.FILES_PATH;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final String fileName = "cars.json";
    private final Validator validator;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper mapper, Validator validator) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seed() throws IOException {
        if (carRepository.count()>0){
            return;
        }
        String items = Files.readString(Path.of(FILES_PATH + fileName));
        SeedCarDto[] seedCarDtos = gson.fromJson(items, SeedCarDto[].class);

        Arrays.stream(seedCarDtos).filter(s->validator.validate(s).isEmpty()).forEach(s->{
            Car car=mapper.map(s,Car.class);
            Set<Part> parts=new HashSet<>();

            int partsCount = getRandomNumberUsingInts(3, 5);
            while (parts.size()!=partsCount){
                Long randomNum =Long.parseLong(String.valueOf(getRandomNumberUsingInts(1, Integer.parseInt(String.valueOf(partRepository.count())))));
                Part part = partRepository.findById(randomNum).orElse(null);
                Boolean contains=false;
                for (Part part1:parts){
                    if (part1.getName().equals(part.getName())){
                        contains=true;
                        break;
                    }
                }
                if (!contains)
                parts.add(part);
            }


            car.setParts(parts);
            carRepository.save(car);
        });
    }

    @Override
    public List<CarDto> getAllCarsByMake(String make) {
        return carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make).stream().map(car -> {
            CarDto carDto=mapper.map(car,CarDto.class);
            return carDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsDto> getAllCars() {
        return carRepository.findAll().stream().map(car -> {
            CarWithPartsDto carDto=mapper.map(car,CarWithPartsDto.class);
//
            return carDto;
        }).collect(Collectors.toList());

    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
