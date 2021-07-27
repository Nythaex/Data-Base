package entity.HospitalDatabase;

import entity.BasicTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "history")
public class History extends BasicTable {
    private Visitations visitations;
    private Diagnoses diagnoses;
    private Medicament medicament;
    private Patient patient;

    public History(Visitations visitations, Diagnoses diagnoses, Medicament medicament) {
        this.visitations = visitations;
        this.diagnoses = diagnoses;
        this.medicament = medicament;
    }

    public History() {
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToOne(optional = false)
    public Visitations getVisitations() {
        return visitations;
    }

    public void setVisitations(Visitations visitations) {
        this.visitations = visitations;
    }
    @OneToOne(optional = false)
    public Diagnoses getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Diagnoses diagnoses) {
        this.diagnoses = diagnoses;
    }
    @OneToOne(optional = false)
    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
