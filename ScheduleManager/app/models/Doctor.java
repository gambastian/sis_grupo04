package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * POJO class to store doctor's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "doctor")
public class Doctor extends Model implements Serializable{

    @Id
    @Column(name = "doctor_id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "doctors")
    private List<Speciality> specialities;
    @ManyToMany(mappedBy="doctors")
    private List<Insurance> afiliatedInsurances;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "default_address")
    private String defaultAddress;
    @Column(name = "email")
    private String email;
    public static Model.Finder<String,Doctor> find = new Model.Finder<String, Doctor>(Doctor.class);

    public Doctor(){}

    public Doctor(String id, String name, List<Speciality> specialities, List<Insurance> afiliatedInsurances, Integer phone, String defaultAddress, String email) {
        this.id = id;
        this.name = name;
        this.specialities = specialities;
        this.afiliatedInsurances = afiliatedInsurances;
        this.phone = phone;
        this.defaultAddress = defaultAddress;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @JsonIgnore
    public List<Insurance> getAfiliatedInsurances() {
        return afiliatedInsurances;
    }

    public void setAfiliatedInsurances(List<Insurance> afiliatedInsurances) {
        this.afiliatedInsurances = afiliatedInsurances;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        return id.equals(doctor.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
