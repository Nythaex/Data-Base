package entity.Football;

import entity.BasicTable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round extends BasicTable {
    private String name;
    private Set<Game> games=new HashSet<>();

    public Round(String name) {
        this.name = name;
    }

    public Round() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "round")
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
