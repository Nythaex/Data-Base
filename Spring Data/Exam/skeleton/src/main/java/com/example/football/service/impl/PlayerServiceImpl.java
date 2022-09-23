package com.example.football.service.impl;

import com.example.football.models.dto.seed.xml.players.SeedPlayerRoot;
import com.example.football.models.dto.seed.xml.stats.SeedStatRoot;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final String PATH="src/main/resources/files/xml/players.xml";
    private final StatService statService;
    private final TownService townService;
    private final TeamService teamService;
    private final PlayerRepository playerRepository;
    private final XmlParser parser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(StatService statService, TownService townService, TeamService teamService,
                             PlayerRepository playerRepository, XmlParser parser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.statService = statService;
        this.townService = townService;
        this.teamService = teamService;
        this.playerRepository = playerRepository;
        this.parser = parser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count()>0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        SeedPlayerRoot seedPlayerRoot = parser.fromFile(PATH, SeedPlayerRoot.class);

        StringBuilder builder = new StringBuilder();
        seedPlayerRoot.getPlayerDtos().stream().filter(playerDto -> {
            Boolean isValid = validationUtil.isValid(playerDto) &&
                    !playerRepository.existsByEmail(playerDto.getEmail());

            if (isValid) {
                builder.append(String.format("Successfully imported Player %s %s - %s%n", playerDto.getFirstName(),playerDto.getLastName(),String.valueOf(playerDto.getPosition())));
                return true;
            } else {
                builder.append("Invalid Player");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(playerDto -> {
            Player player = modelMapper.map(playerDto, Player.class);
            player.setTown(townService.getTownByName(playerDto.getTown().getName()));
            player.setTeam(teamService.getTeamByName(playerDto.getTeam().getName()));
            player.setStat(statService.getStatById(playerDto.getStat().getId()));


            playerRepository.save(player);
        });
        return builder.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder builder=new StringBuilder();
        List<Player> bestPlayers = playerRepository.getBestPlayers(LocalDate.of(1995,01,01),LocalDate.of(2003,01,01));
        bestPlayers.forEach(player -> {
             builder.append(String.format("Player - %s %s\n" +
                     "\tPosition - %s\n" +
                     "\tTeam - %s\n" +
                     "\tStadium - %s\n",player.getFirstName(),player.getLastName(),player.getPosition(),player.getTeam().getName(),player.getTeam().getStadiumName()));

         });

        return builder.toString().trim();
    }
}
