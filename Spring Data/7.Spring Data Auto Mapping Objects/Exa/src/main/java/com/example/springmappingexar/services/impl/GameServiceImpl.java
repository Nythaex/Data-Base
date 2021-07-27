package com.example.springmappingexar.services.impl;

import com.example.springmappingexar.models.dtos.AddGameDto;
import com.example.springmappingexar.models.dtos.GameDetailsDto;
import com.example.springmappingexar.models.entity.Game;
import com.example.springmappingexar.repos.GameRepository;
import com.example.springmappingexar.services.interfaces.GameService;
import com.example.springmappingexar.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper mapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(AddGameDto addGameDto) {
        Set<ConstraintViolation<AddGameDto>> violation = validationUtil.violation(addGameDto);
        if (!violation.isEmpty()){
            violation.stream().map(ConstraintViolation::getMessage).forEach(s-> System.out.println(s));
            return;
        }
        Game game=mapper.map(addGameDto,Game.class);
        gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        Game byId = gameRepository.findById(id).orElse(null);
        if (byId==null){
            System.out.println("No such game");
        }else {
            gameRepository.delete(byId);
        }
    }

    @Override
    public void editGame(Long id, String[] commands) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game==null){
            System.out.println("No such game");
        }else {
            for (int i = 2; i < commands.length; i++) {
                String []tokens=commands[i].split("=");
                switch (tokens[0]){
                    case "price":
                        game.setPrice(BigDecimal.valueOf(Double.parseDouble(tokens[1])));
                        break;
                    case "title":
                         game.setTitle(tokens[1]);
                        break;
                    case "size":
                        game.setSize(Double.parseDouble(tokens[1]));
                        break;
                    case "trailer":
                        game.setTrailer(tokens[1]);
                        break;
                    case "thubnailURL":
                        game.setImageThumbnail(tokens[1]);
                        break;
                    case "description":
                        game.setDescription(tokens[1]);
                        break;
                    case "releaseDate":
                        game.setReleaseDate(LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        break;
                }
            }
        }

           gameRepository.save(game);
    }

    @Override
    public List<GameDetailsDto> getAllGames() {
        List<Game> collect = new ArrayList<>(gameRepository.findAll());
        List<GameDetailsDto> games=new ArrayList<>();
        GameDetailsDto gameDtp=mapper.map(collect.get(0),GameDetailsDto.class);


        collect.forEach(s->{
            games.add(mapper.map(s, GameDetailsDto.class));
        });

        return games;
    }

    @Override
    public GameDetailsDto getGameDetails(String title) {
        return mapper.map(gameRepository.findByTitle(title),GameDetailsDto.class);
    }
}
