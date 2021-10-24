package com.example.football.service.impl;

import com.example.football.models.dto.seed.json.SeedTownDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class TownServiceImpl implements TownService {
    private final String PATH="src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importTowns() throws IOException {
        SeedTownDto[] seedTownDtos = gson.fromJson(readTownsFileContent(), SeedTownDto[].class);
        StringBuilder builder=new StringBuilder();
        Arrays.stream(seedTownDtos).filter(seedTownDto -> {
            Boolean isValid=validationUtil.isValid(seedTownDto)&&
                    !townRepository.existsByName(seedTownDto.getName());
            if (isValid){
                builder.append(String.format("Successfully imported Town %s - %s%n",seedTownDto.getName(),String.valueOf(seedTownDto.getPopulation())));
                return true;
            }else {
                builder.append("Invalid Town");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(seedTownDto -> {
            Town town= modelMapper.map(seedTownDto, Town.class);

            townRepository.save(town);
        });
        return builder.toString().trim();

    }

    @Override
    public Town getTownByName(String name) {
        return townRepository.findTownByName(name);
    }

}
