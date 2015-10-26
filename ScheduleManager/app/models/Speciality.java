package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * POJO class to store speciality's information
 *
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "speciality")
public class Speciality implements Serializable{

    @Id
    @Column(name = "speciality_id")
    private String id;
    @Column(name = "name")
    private String name;

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
}
