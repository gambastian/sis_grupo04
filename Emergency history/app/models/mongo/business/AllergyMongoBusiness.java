package models.mongo.business;

import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.mongo.AllergyMongo;
import org.jongo.MongoCollection;

/**
 * Created by Ger on 11/10/2015.
 */
public class AllergyMongoBusiness {

    public static MongoCollection allergies() {
        return MongoManager.jongo.getCollection("allergy");
    }

    public static void insert(AllergyMongo allergy) {
        allergies().save(allergy);
    }
    public static void cleanAll() {
        allergies().remove("{}");
    }
    
    public static  Iterable<AllergyMongo> findAll() {
        Iterable<AllergyMongo> allergies = allergies().find().as(AllergyMongo.class);
        return allergies;
    }

}