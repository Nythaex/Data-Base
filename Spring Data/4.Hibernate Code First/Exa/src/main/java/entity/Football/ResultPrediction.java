package entity.Football;

import entity.BasicTable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BasicTable {
    private String prediction;
    private Set<BetGames> betGames=new HashSet<>();
    public ResultPrediction(String prediction) throws Exception {
        setPrediction(prediction);
    }

    public ResultPrediction() {
    }


    public String getPrediction() {
        return prediction;
    }


    public void setPrediction(String prediction) throws Exception {
        if (prediction.equals("Home Team")||prediction.equals("Draw Game")||prediction.equals("Away Team Win")){
            this.prediction = prediction;
        }else {
            throw new Exception();
        }
    }
    @OneToMany(mappedBy = "resultPrediction")
    public Set<BetGames> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGames> betGames) {
        this.betGames = betGames;
    }
}
