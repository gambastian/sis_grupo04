package models.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import java.util.Date;

/**
 * POJO class to store Allergy's information
 *
 * @author Germán Rojas
 */

public class PatientPathologyMongo {

    @JsonProperty("_id")
    public ObjectId id;
    public Integer id_ext;
    public Integer patientId;
    public Integer pathologyId;
}
