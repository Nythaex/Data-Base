package com.example.football.models.dto.seed.xml.players;

import com.example.football.models.entity.Position;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeedPlayerDto {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement(name = "position")
    private Position position;
    @XmlElement(name = "town")
    private XmlTown town;
    @XmlElement(name = "team")
    private XmlTeam team;
    @XmlElement(name = "stat")
    private XmlStat stat;

    public SeedPlayerDto() {

    }

    public SeedPlayerDto(String firstName, String lastName, String email, String birthDate, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
    }


    @Size(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Size(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public XmlTown getTown() {
        return town;
    }

    public void setTown(XmlTown town) {
        this.town = town;
    }

    public XmlTeam getTeam() {
        return team;
    }

    public void setTeam(XmlTeam team) {
        this.team = team;
    }

    public XmlStat getStat() {
        return stat;
    }

    public void setStat(XmlStat stat) {
        this.stat = stat;
    }
}
