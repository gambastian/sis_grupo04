package models.mongo.business;

import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.mongo.MedicalHistoryMongo;
import org.jongo.MongoCollection;
import models.MedicalHistory;

/**
 * Created by Ger on 11/10/2015.
 */
public class MedicalHistoryMongoBusiness {

    public static MongoCollection medicalHistories() {
        return MongoManager.jongo.getCollection("medicalHistory");
    }

    public static void insert(MedicalHistoryMongo medicalHistory) {
        medicalHistories().save(medicalHistory);
    }
    public static void cleanAll() {
        medicalHistories().remove("{}");
    }

    public static MedicalHistoryMongo findByPatientId(Integer patientId) {
        return medicalHistories().findOne("{patientId: #}", patientId).as(MedicalHistoryMongo.class);
    }
    public static  Iterable<MedicalHistoryMongo> findAll() {
        Iterable<MedicalHistoryMongo> medicalHistories = medicalHistories().find().as(MedicalHistoryMongo.class);
        return medicalHistories;
    }

}