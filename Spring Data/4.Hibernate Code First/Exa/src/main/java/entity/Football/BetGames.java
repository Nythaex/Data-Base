package entity.Football;

import entity.BasicTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bet_games")
public class BetGames extends BasicTable {
    private Game game;
    private Bet bet;
    private ResultPrediction resultPrediction;

    public BetGames(Game game, Bet bet, ResultPrediction resultPrediction) {
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public BetGames() {
    }
    @ManyToOne
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    @ManyToOne
    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @ManyToOne
    @JoinColumn(name = "result_prediction",nullable = false)
    public ResultPrediction getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
