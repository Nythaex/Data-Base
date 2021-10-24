package com.example.bonusexa.services;

import com.example.bonusexa.models.dto.printDtos.ex4.CarWithPartsDto;
import com.example.bonusexa.models.dto.printDtos.ex2.PrintCarsFromMakeRoot;
import com.example.bonusexa.models.dto.printDtos.ex4.PrintAllCarsWithParts;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {
    void seed() throws IOException, JAXBException;
   PrintCarsFromMakeRoot getAllCarsByMake(String make);

    PrintAllCarsWithParts getAllCars();

}
