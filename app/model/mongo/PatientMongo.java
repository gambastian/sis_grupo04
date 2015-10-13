package model.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import java.util.Date;

/**
 * POJO class to store Allergy's information
 *
 * @author Germán Rojas
 */

public class PatientMongo {

	public static final long serialVersionUID = 1L;
	
	@JsonProperty("_id")
    public ObjectId id;
    public Integer id_ext;
	public String name;
	public String login;
	public Date birthDate;
	public String bloodType;
	public Integer heightCm;
	public Integer weightGr;
	public boolean active;
}
