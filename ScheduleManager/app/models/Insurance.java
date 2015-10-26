package models;

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
public class Insurance implements Serializable{

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
}
