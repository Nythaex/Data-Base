package com.example.football.models.dto.seed.xml.players;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPlayerRoot {
    @XmlElement(name = "player")
    private List<SeedPlayerDto> playerDtos;


    public SeedPlayerRoot(List<SeedPlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }
    public SeedPlayerRoot() {
    }

    public List<SeedPlayerDto> getPlayerDtos() {
        return playerDtos;
    }

    public void setPlayerDtos(List<SeedPlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }
}
