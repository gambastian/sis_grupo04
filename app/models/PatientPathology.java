package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * POJO class to store patients and pathologies relation
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "patient_pathology")
public class PatientPathology extends Model implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "pathology_id")
    private Integer pathologyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getPathologyId() {
        return pathologyId;
    }

    public void setPathologyId(Integer pathologyId) {
        this.pathologyId = pathologyId;
    }
}
