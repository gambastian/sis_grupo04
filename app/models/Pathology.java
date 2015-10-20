package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import models.mongo.PathologyMongo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO class to store Pathology's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "pathology")
public class Pathology extends Model implements Serializable{

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "triage")
    private Integer triage;

    @ManyToMany
    @JoinTable(name = "patient_pathology",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pathology_id", referencedColumnName = "id"))
    private List<Patient> patients;

    public static Finder<Integer, Pathology> find = new Finder<Integer,Pathology>(Pathology.class);
    public static Finder<Integer, Pathology> findH2 = new Finder<Integer,Pathology>(Pathology.class);

    public Pathology(Integer id, String name, Integer triage) {
        this.id = id;
        this.name = name;
        this.triage = triage;
    }

    /**
     * builds a resourse pathology with a mongo pathology
     * @param pathologyMongo
     */
    public Pathology(PathologyMongo pathologyMongo){

        this.id = pathologyMongo.id_ext;
        this.name = pathologyMongo.name;
        this.triage = pathologyMongo.triage;
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

    public Integer getTriage() {
        return triage;
    }

    public void setTriage(Integer triage) {
        this.triage = triage;
    }

    @JsonIgnore
    public List<Patient> getPatients() {
        if(patients == null){
            patients = new ArrayList<>();
        }
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Pathology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", triage=" + triage +
                '}';
    }
}
