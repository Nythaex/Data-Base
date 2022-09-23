package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.json.SeedPassengersDto;
import softuni.exam.models.dtos.json.SeedTownDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final String PATH="src/main/resources/files/json/passengers.json";
    private final TownService townService;
    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PassengerServiceImpl(TownService townService, PassengerRepository passengerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townService = townService;
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count()>0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        SeedPassengersDto[] seedPassengersDtos = gson.fromJson(readPassengersFileContent(), SeedPassengersDto[].class);
        StringBuilder builder=new StringBuilder();
        Arrays.stream(seedPassengersDtos).filter(seedPassengersDto -> {


            boolean isValid=validationUtil.isValid(seedPassengersDto)&&
                    !passengerRepository.existsByEmail(seedPassengersDto.getEmail());
            if (isValid){
                builder.append(String.format("Successfully imported Passenger %s - %s%n",seedPassengersDto.getLastName(),seedPassengersDto.getEmail()));
                return true;
            }else {
                builder.append("Invalid Passenger");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(seedPassengerDto -> {
            Passenger passenger= modelMapper.map(seedPassengerDto, Passenger.class);
            passenger.setTown(townService.getTownByName(seedPassengerDto.getTown()));
            passengerRepository.save(passenger);
        });
        return builder.toString().trim();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        List<Passenger> passengers=passengerRepository.getOrderedPassengers();
        StringBuilder builder=new StringBuilder();
        passengers.forEach(passenger -> {
            builder.append(String.format("Passenger %s  %s\n" +
                    "\tEmail - %s\n" +
                    "Phone - %s\n" +
                    "\tNumber of tickets - %d\n",passenger.getFirstName(),passenger.getLastName(),passenger.getEmail(),passenger.getPhoneNumber(),
                    passenger.getTicketSet().size()));
        });

        return builder.toString().trim();
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
      return  passengerRepository.findByEmail(email);
    };
}
