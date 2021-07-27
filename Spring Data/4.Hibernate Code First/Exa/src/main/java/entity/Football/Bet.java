package entity.Football;

import entity.BasicTable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet extends BasicTable {
    private BigDecimal betMoney;
    private String dateTime;
    private  Users user;
    private Set<BetGames> betGames=new HashSet<>();

    public Bet(BigDecimal betMoney, String dateTime, Users user) {
        this.betMoney = betMoney;
        this.dateTime = dateTime;
        this.user = user;
    }

    public Bet() {
    }

    @Column(name = "bet_money",precision = 10,scale = 2)
    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    @Column(name = "date_time",columnDefinition = "DATETIME")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "bet")
    public Set<BetGames> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGames> betGames) {
        this.betGames = betGames;
    }
}
