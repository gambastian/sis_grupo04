package models.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import java.util.Date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO class to store Allergy's information
 *
 * @author Germán Rojas
 */

public class MedicalHistoryMongo  implements Serializable {
	@JsonProperty("_id")
	public ObjectId id;
	public Integer patientId;
	public PatientMongo patient;
    public List<PathologyMongo> pathologies;
    public List<AllergyMongo> allergies;
    public List<MedicalProcedureMongo> medicalProcedures;
    public List<DiagnosticImageMongo> diagnosticImages;

    
}
