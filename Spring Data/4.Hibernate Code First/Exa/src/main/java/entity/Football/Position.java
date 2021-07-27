package entity.Football;

import entity.BasicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position  {
    private int id;
    private String positionDescription;


    public Position() {
    }

    public Position(int id, String positionDescription) {
        this.id = id;
        this.positionDescription = positionDescription;
    }

    @Id
    @Column(length = 2)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
