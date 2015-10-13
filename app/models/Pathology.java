package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
}
