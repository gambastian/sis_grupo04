package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * POJO class to store appointment's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "appointment")
public class Appointment extends Model implements Serializable{

    @Id
    @Column(name = "appointment_id")
    private String id;
    @Column(name = "patient")
    private String patientId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="speciality_id")
    private Speciality speciality;
    @Column(name = "address")
    private String address;
    @Column(name = "time")
    private Date time;
    @Column(name = "insurance_id")
    private Insurance insurance;

    public Appointment(){}

    public Appointment(String id, String patientId, Doctor doctor, Speciality speciality, String address, Date time, Insurance insurance) {
        this.id = id;
        this.patientId = patientId;
        this.doctor = doctor;
        this.speciality = speciality;
        this.address = address;
        this.time = time;
        this.insurance = insurance;
    }

    public static Model.Finder<String,Appointment> find = new Model.Finder<String, Appointment>(Appointment.class);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
