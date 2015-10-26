package models;

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
public class Appointment implements Serializable{

    @Id
    @Column(name = "appointment_id")
    private String id;
    @Column(name = "patient")
    private String patientId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    @Column(name = "speciality")
    private SpecialityEnum speciality;
    @Column(name = "address")
    private String address;
    @Column(name = "time")
    private Date time;
    @Column(name = "insurance_id")
    private Insurance insurance;

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

    public SpecialityEnum getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityEnum speciality) {
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
}
