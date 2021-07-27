package entity.HospitalDatabase;

import entity.BasicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diagnoses")
public class Diagnoses extends BasicTable {
    private String name;
    private String comment;

    public Diagnoses() {
    }

    public Diagnoses(String name, String comment) throws Exception {
        setName(name);
        setComments(comment);
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

    @Column(nullable = false)
    public String getComments() {
        return comment;
    }

    public void setComments(String comment) throws Exception {
        if (this.comment.length()<1||comment==null){
            throw new Exception();
        }
        this.comment = comment;
    }
}
