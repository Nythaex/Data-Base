package com.example.springmappingexar.services.interfaces;

import com.example.springmappingexar.models.dtos.GameDetailsDto;
import com.example.springmappingexar.models.dtos.AddGameDto;

import java.util.List;

public interface GameService {
    void addGame(AddGameDto addGameDto);
    void deleteGame(Long id);
    void editGame(Long id, String [] commands);

    List<GameDetailsDto> getAllGames();

    GameDetailsDto getGameDetails(String title);
}
