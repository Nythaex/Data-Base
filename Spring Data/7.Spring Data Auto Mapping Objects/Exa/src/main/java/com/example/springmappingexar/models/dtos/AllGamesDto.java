package com.example.springmappingexar.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class AllGamesDto {
   private Set<GameDetailsDto> games=new HashSet<>();

   public Set<GameDetailsDto> getGames() {
      return games;
   }

   public void setGames(Set<GameDetailsDto> games) {
      this.games = games;
   }

   public AllGamesDto() {
   }

   public AllGamesDto(Set<GameDetailsDto> games) {
      this.games = games;
   }
}
