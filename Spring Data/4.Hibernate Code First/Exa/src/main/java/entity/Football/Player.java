package entity.Football;

import entity.BasicTable;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BasicTable {
    private String name;
    private int squadNumber;
    private Team team;
    private Position position;
    private boolean isInjured;

    public Player() {
    }

    public Player(String name, int squad_number, Team team, Position position, boolean isInjured) {
        this.name = name;
        squadNumber = squad_number;
        this.team = team;
        this.position = position;
        this.isInjured = isInjured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "squad_number")
    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squad_number) {
        squadNumber = squad_number;
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToOne
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isInjured() {
        return isInjured;
    }
     @Column(name = "is_injured")
    public void setInjured(boolean injured) {
        isInjured = injured;
    }
}
