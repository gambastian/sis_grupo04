package controllers;

import models.*;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import models.business.*;
import models.mongo.business.*;
import models.mongo.*;
import persistence.IMedicalHistoryDao;
import persistence.impl.MedicalHistoryDaoImpl;

/**
 * Created by Ger on 10/12/15.
 */

public class ScheduleToMongo extends Controller {

    public Result executeAllergies() {

    	String name;
	    String listAllergies = "";

	    List<Allergy> allAllergies = AllergyBusiness.findAll();

	    AllergyMongoBusiness.cleanAll();

	    for(Allergy allergy : allAllergies){
	    	AllergyMongo allergyMongo = new AllergyMongo();
	    	allergyMongo.id_ext = allergy.getId();
	    	allergyMongo.name = allergy.getName();
			listAllergies = listAllergies + "P: " + allergyMongo.name;
	    	AllergyMongoBusiness.insert(allergyMongo);
	    }
		
	    for(AllergyMongo allergyMongoItem : AllergyMongoBusiness.findAll()){
	    	listAllergies = listAllergies + "M: " + allergyMongoItem.name;
	    }

		return ok(index.render(listAllergies));
    }
    public Result execute(){
    	String listHistories = "";

    	IMedicalHistoryDao historyDao = new MedicalHistoryDaoImpl();
    	List<Patient> allPatients = PatientBusiness.findAll();

    	MedicalHistoryMongoBusiness.cleanAll();
    	for(Patient patient : allPatients){
	    	MedicalHistoryMongo medicalHistoryMongo = new MedicalHistoryMongo();
	    	medicalHistoryMongo.patientId = patient.getId();
			listHistories = listHistories + "P: " + medicalHistoryMongo.patientId;
	    	MedicalHistoryMongoBusiness.insert(medicalHistoryMongo);
	    }

	    for(MedicalHistoryMongo medicalHistoryMongoItem : MedicalHistoryMongoBusiness.findAll()){
	    	listHistories = listHistories + "M: " + medicalHistoryMongoItem.patientId;
	    }
	    
	    return ok(index.render(listHistories));

    }
}
