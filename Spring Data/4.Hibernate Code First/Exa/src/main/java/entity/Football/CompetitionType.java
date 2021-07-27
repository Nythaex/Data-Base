package entity.Football;

import entity.BasicTable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "competition_type")
public class CompetitionType extends BasicTable {
    private String type;

    public CompetitionType(String type) {
        this.type = type;
    }

    public CompetitionType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
