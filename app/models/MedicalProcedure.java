package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import models.mongo.MedicalProcedureMongo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by SEBASTIAN on 12/10/2015.
 */
@Entity
@Table(name = "medical_procedure")
public class MedicalProcedure extends Model implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(name = "date")
    private Date date;

    public static Finder<Integer, MedicalProcedure> find = new Finder<Integer,MedicalProcedure>("secundary", Integer.class, MedicalProcedure.class);
    public static Finder<Integer, MedicalProcedure> findH2 = new Finder<Integer,MedicalProcedure>(MedicalProcedure.class);

    public MedicalProcedure(Integer id, String name, Patient patient, Date date) {
        this.id = id;
        this.name = name;
        this.patient = patient;
        this.date = date;
    }

    public MedicalProcedure(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    /**
     * builds a resource medical procedure from a mongo medical procedure
     * @param medicalProcedureMongo
     */
    public MedicalProcedure(MedicalProcedureMongo medicalProcedureMongo){
        this.id = medicalProcedureMongo.id_ext;
        this.name = medicalProcedureMongo.name;
        this.date = medicalProcedureMongo.date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MedicalProcedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
