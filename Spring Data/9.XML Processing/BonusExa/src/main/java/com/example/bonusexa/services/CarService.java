package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.CarWithPartsDto;
import com.example.bonusexa.models.dto.printDtos.CarDto;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seed() throws IOException;
   List<CarDto> getAllCarsByMake(String make);

    List<CarWithPartsDto> getAllCars();

}
