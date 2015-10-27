package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * POJO class to store appointment's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "insurance")
public class Insurance extends Model implements Serializable{

    @Id
    @Column(name = "insurance_id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
            name="insurance_doctor",
            joinColumns={@JoinColumn(name="insurance_id", referencedColumnName="insurance_id")},
            inverseJoinColumns={@JoinColumn(name="doctor_id", referencedColumnName="doctor_id")})
    private List<Doctor> doctors;

    public static Model.Finder<String,Insurance> find = new Model.Finder<String, Insurance>(Insurance.class);

    public Insurance(){

    }

    public Insurance(String id, String name) {
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

        Insurance insurance = (Insurance) o;

        return id.equals(insurance.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
