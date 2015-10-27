package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * POJO class to store speciality's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "speciality")
public class Speciality extends Model implements Serializable{

    @Id
    @Column(name = "speciality_id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(name = "doctor_speciality",
            joinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id"))
    private List<Doctor> doctors;

    public static Model.Finder<String,Speciality> find = new Model.Finder<String, Speciality>(Speciality.class);

    public Speciality(){}

    public Speciality(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
