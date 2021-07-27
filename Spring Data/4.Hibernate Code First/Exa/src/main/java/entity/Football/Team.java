package entity.Football;

import entity.BasicTable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BasicTable {
    private String name;
    private String logo;
    private String initials;
    private Color primary_kid;
    private Color secondary_kit;
    private Town town;
    private BigDecimal budget;
    private Set<Player> playerSet =new HashSet<>();

    public Team(String name, String logo, String initials, Color primary, Color secondary_kit, Town town, BigDecimal budget) {
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.primary_kid = primary;
        this.secondary_kit = secondary_kit;
        this.town = town;
        this.budget = budget;
    }

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(columnDefinition = "VARCHAR(3)")
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @ManyToOne
    @JoinColumn(name = "primary_kid_color")
    public Color getPrimary_kid() {
        return primary_kid;
    }


    public void setPrimary_kid(Color primary_kid) {
        this.primary_kid = primary_kid;
    }
    @ManyToOne
    @JoinColumn(name = "secondary_kid_color")
    public Color getSecondary_kit() {
        return secondary_kit;
    }

    public void setSecondary_kit(Color secondary) {
        this.secondary_kit = secondary;
    }
    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(precision = 10,scale = 2)
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @OneToMany(mappedBy = "team")
    public Set<Player> getPlayersSet() {
        return playerSet;
    }

    public void setPlayersSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }
}
