package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.seed.json.SeedCarDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final String PATH="src/main/resources/files/json/cars.json";
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return carRepository.count()>0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importCars() throws IOException {
        SeedCarDto[] seedCarDtos = gson.fromJson(readCarsFileContent(), SeedCarDto[].class);
        StringBuilder builder=new StringBuilder();

        Arrays.stream(seedCarDtos).filter(s1->{
            if (validationUtil.isValid(s1)){
                builder.append(String.format("Successfully imported car - %s - %s%n",s1.getMake(), s1.getModel()));
                return true;
            }else {
                builder.append(String.format("Invalid car%n"));
                return false;
            }
        }).forEach(s1->{
            Car car=modelMapper.map(s1,Car.class);
            carRepository.save(car);
        });

        return builder.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder builder=new StringBuilder();
        List<Car> allByOrderByPicturesDescMakeAsc = carRepository.findAllByOrderByPicturesDescMakeAsc();
        allByOrderByPicturesDescMakeAsc.forEach(car -> {
            builder.append(String.format("Car make - %s, model - %s\n" +
                    "\tKilometers - %d\n" +
                    "\tRegistered on - %s\n" +
                    "\tNumber of pictures - %d\n",car.getMake(),car.getModel(),car.getKilometers(),String.valueOf(car.getRegisteredOn()),car.getPictures().size()));

        });

        return builder.toString().trim();
    }

    @Override
    public Car getCarById(Long id) {
      return   carRepository.findById(id).orElse(null);
    }
}
