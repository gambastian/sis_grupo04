package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO class to store Allergy's information
 *
 * @author Sebastian Gamba Pinilla
 */


@Table(name = "allergy")
@Entity
public class Allergy extends Model implements Serializable{

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "patient_allergy",
        joinColumns = @JoinColumn(name = "allergy_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private List<Patient> patients;

    public static Finder<Integer, Allergy> find = new Finder<Integer,Allergy>(Allergy.class);

    public Allergy(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    public List<Patient> getPatients() {
        if(patients == null){
            this.patients = new ArrayList<>();
        }
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
