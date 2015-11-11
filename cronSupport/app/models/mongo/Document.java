package models.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import java.util.Date;

/**
 * POJO class to store Document's information
 *
 * @author Germán Rojas
 */

public class Document {

    @JsonProperty("_id")
    public ObjectId id;
    public String action;
    public String content;
}