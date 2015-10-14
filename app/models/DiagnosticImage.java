package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * POJO class to store DiagnosticImage's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "diagnostic_image")
public class DiagnosticImage extends Model implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private Date date;

    public static Finder<Integer, DiagnosticImage> find = new Finder<Integer,DiagnosticImage>("secundary", Integer.class, DiagnosticImage.class);
    public static Finder<Integer, DiagnosticImage> findH2 = new Finder<Integer,DiagnosticImage>(DiagnosticImage.class);

    public DiagnosticImage(Integer id, String name, Patient patient, String type, Date date) {
        this.id = id;
        this.name = name;
        this.patient = patient;
        this.type = type;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
