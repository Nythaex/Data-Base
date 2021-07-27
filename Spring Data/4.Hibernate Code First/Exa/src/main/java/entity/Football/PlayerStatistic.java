package entity.Football;


import entity.BasicTable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "player_statistics")
public class PlayerStatistic extends BasicTable {

    private Game game;
    private Player player;
    private int scoredGoals;
    private int playerAssists;
    private int playedMinutes;

    public PlayerStatistic(Game game, Player player, int scoredGoals, int playerAssists, int playedMinutes) {
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutes = playedMinutes;
    }

    public PlayerStatistic() {
    }

    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }
}
