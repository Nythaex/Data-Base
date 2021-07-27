package entity.HospitalDatabase;

import entity.BasicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicament")
public class Medicament extends BasicTable {
    private String name;

    public Medicament(String name) throws Exception {
        setName(name);
    }

    public Medicament() {
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length()<1){
            throw new Exception();
        }
        this.name = name;
    }
}
