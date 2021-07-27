package entity.Football;

import entity.BasicTable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
public class Color extends BasicTable {
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public Color() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
