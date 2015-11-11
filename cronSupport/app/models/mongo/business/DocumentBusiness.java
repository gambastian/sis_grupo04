package models.mongo.business;

import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.mongo.Document;
import org.jongo.MongoCollection;

/**
 * Created by Ger on 11/10/2015.
 */
public class DocumentBusiness {

    public static MongoCollection documents() {
        return MongoManager.jongo.getCollection("document");
    }

    public static void insert(Document document) {
        documents().save(document);
    }
    public static void cleanAll() {
        documents().remove("{}");
    }
    public static void remove(ObjectId id) {
        documents().remove(id);
    }
    
    public static  Iterable<Document> findAll() {
        Iterable<Document> documents = documents().find().as(Document.class);
        return documents;
    }

}