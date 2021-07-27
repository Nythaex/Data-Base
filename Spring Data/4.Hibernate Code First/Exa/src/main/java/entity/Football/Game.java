package entity.Football;

import entity.BasicTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BasicTable {
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String dateTime;
    private double homeTeamWinBetRate;
    private double awayTeamWinBetRate;
    private double drawGameBetRate;
    private Round round;
    private Competition competition;
    private Set<BetGames> betGames=new HashSet<>();

    public Game(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, String dateTime, double homeTeamWinBetRate, double awayTeamWinBetRate, double drawGameBetRate, Round round, Competition competition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.dateTime = dateTime;
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.drawGameBetRate = drawGameBetRate;
        this.round = round;
        this.competition = competition;
    }

    public Game() {
    }

    @ManyToOne
    @JoinColumn(name = "home_team")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }
    @ManyToOne
    @JoinColumn(name = "away_team")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
    @Column(name = "home_team_goals")
    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }
    @Column(name = "away_team_goals")
    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
    @Column(name = "date_time",columnDefinition = "DATETIME")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    @Column(name = "home_team_win_bet_rate")
    public double getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }
    @Column(name = "away_team_win_bet_rate")
    public double getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }
    @Column(name = "draw_game_bet_rate")
    public double getDrawGameBetRate() {
        return drawGameBetRate;
    }

    public void setDrawGameBetRate(double drawGameBetRate) {
        this.drawGameBetRate = drawGameBetRate;
    }

    @ManyToOne
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
      @ManyToOne
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    @OneToMany(mappedBy = "game")
    public Set<BetGames> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGames> betGames) {
        this.betGames = betGames;
    }
}
