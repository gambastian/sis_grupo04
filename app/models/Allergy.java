package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * POJO class to store Allergy's information
 *
 * @author Sebastian Gamba Pinilla
 */


@Table(name = "allergy")
@Entity
public class Allergy extends Model implements Serializable{

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

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
    public static Finder<Integer, Allergy> find = new Finder<Integer,Allergy>("secundary", Integer.class, Allergy.class);
}
