package entity.HospitalDatabase;

import entity.BasicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "visitations")
public class Visitations extends BasicTable {
    private Date date;
    private String comment;

    public Visitations(Date date,String comment) throws Exception {
        setDate(date);
        setComments(comment);
    }

    public Visitations() {
    }

    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws Exception {
        if (date==null){
            throw new Exception();
        }
        this.date = date;
    }
    @Column(nullable = false)
    public String getComments() {
        return comment;
    }

    public void setComments(String comment) throws Exception {
        if (this.comment.length()<1||date==null){
            throw new Exception();
        }
        this.comment = comment;
    }
}
