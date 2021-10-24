package com.example.football.service.impl;

import com.example.football.models.dto.seed.json.SeedTeamDto;
import com.example.football.models.dto.seed.json.SeedTownDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {
    private final String PATH="src/main/resources/files/json/teams.json";
    private final TeamRepository teamRepository;
    private final TownService townService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TownService townService, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.townService = townService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return teamRepository.count()>0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importTeams() throws IOException {
        SeedTeamDto[] seedTeamDtos = gson.fromJson(readTeamsFileContent(), SeedTeamDto[].class);
        StringBuilder builder=new StringBuilder();
        Arrays.stream(seedTeamDtos).filter(seedTeamDto -> {
            Boolean isValid=validationUtil.isValid(seedTeamDto)&&
                    !teamRepository.existsByName(seedTeamDto.getName());
            if (isValid){
                builder.append(String.format("Successfully imported Team %s - %d%n",seedTeamDto.getName(),seedTeamDto.getFanBase()));
                return true;
            }else {
                builder.append("Invalid Team");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(seedTeamDto -> {
            Team team = modelMapper.map(seedTeamDto, Team.class);
            team.setTown(townService.getTownByName(seedTeamDto.getTownName()));
            teamRepository.save(team);
        });
        return builder.toString().trim();
    }

    @Override
    public Team getTeamByName(String name) {
      return   teamRepository.findByName(name);
    }
}
