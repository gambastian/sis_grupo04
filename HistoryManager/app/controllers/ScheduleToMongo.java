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
import java.util.Arrays;
import java.util.ArrayList;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
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
    	List<Patient> allPatients = PatientBusiness.findAll();

    	MedicalHistoryMongoBusiness.cleanAll();
    	for(Patient patient : allPatients){
	    	MedicalHistoryMongo medicalHistoryMongo = new MedicalHistoryMongo();
	    	medicalHistoryMongo.patientId = patient.getId();

	    	PatientMongo patientMongo = new PatientMongo();
	    	patientMongo.id_ext = patient.getId();
	    	patientMongo.name = patient.getName();
	    	patientMongo.login = patient.getLogin();
	    	patientMongo.birthDate = patient.getBirthDate();
	    	patientMongo.bloodType = patient.getBloodType();
	    	patientMongo.heightCm = patient.getHeightCm();
	    	patientMongo.weightGr = patient.getWeightGr();
	    	patientMongo.active = patient.isActive();

	    	medicalHistoryMongo.patient = patientMongo;
	    	
			//listHistories = listHistories + "P: " + medicalHistoryMongo.patientId;

			//Get the medical history of the patient
			IMedicalHistoryDao historyDao = new MedicalHistoryDaoImpl();
			MedicalHistory historyFromPostgress = historyDao.obtainMedicalHistoryByPatientIdPostgres(medicalHistoryMongo.patientId);

			
			//Get pathologies for the history
			List<Pathology> patientPathologies = historyFromPostgress.getPathologies();
			List<PathologyMongo> pathologyMongoList = new ArrayList<PathologyMongo>();
			for(Pathology pathology : patientPathologies){
				PathologyMongo pathologyMongo = new PathologyMongo();
				pathologyMongo.id_ext = pathology.getId();
				pathologyMongo.name = pathology.getName();
				pathologyMongo.triage = pathology.getTriage();
				pathologyMongoList.add(pathologyMongo);
			}
			medicalHistoryMongo.pathologies = pathologyMongoList;

			//Get allergies for the history
			List<Allergy> patientAllergies = historyFromPostgress.getAllergies();
			List<AllergyMongo> allergyMongoList = new ArrayList<AllergyMongo>();
			for(Allergy allergy : patientAllergies){
				AllergyMongo allergyMongo = new AllergyMongo();
				allergyMongo.id_ext = allergy.getId();
				allergyMongo.name = allergy.getName();
				allergyMongoList.add(allergyMongo);
			}
			medicalHistoryMongo.allergies = allergyMongoList;

			//Get Medical procedures for the history
    		List<MedicalProcedure> patientMedicalProcedures = historyFromPostgress.getMedicalProcedures();
			List<MedicalProcedureMongo> medicalProcedureMongoList = new ArrayList<MedicalProcedureMongo>();
			for(MedicalProcedure medicalProcedure : patientMedicalProcedures){
				MedicalProcedureMongo medicalProcedureMongo = new MedicalProcedureMongo();
				medicalProcedureMongo.id_ext = medicalProcedure.getId();
				medicalProcedureMongo.name = medicalProcedure.getName();
				medicalProcedureMongo.date = medicalProcedure.getDate();
				medicalProcedureMongoList.add(medicalProcedureMongo);
			}
			medicalHistoryMongo.medicalProcedures = medicalProcedureMongoList;

    		//Get Diagnostic Images for the history
     		List<DiagnosticImage> patientDiagnosticImages = historyFromPostgress.getDiagnosticImages();
     		List<DiagnosticImageMongo> diagnosticImageMongoList = new ArrayList<DiagnosticImageMongo>();
     		for(DiagnosticImage diagnosticImage : patientDiagnosticImages){
				DiagnosticImageMongo diagnosticImageMongo = new DiagnosticImageMongo();
				diagnosticImageMongo.id_ext = diagnosticImage.getId();
				diagnosticImageMongo.name = diagnosticImage.getName();
				diagnosticImageMongo.type = diagnosticImage.getType();
				diagnosticImageMongo.date = diagnosticImage.getDate();
				diagnosticImageMongoList.add(diagnosticImageMongo);
			}
			medicalHistoryMongo.diagnosticImages = diagnosticImageMongoList;

	    	MedicalHistoryMongoBusiness.insert(medicalHistoryMongo);
	    }


	    for(MedicalHistoryMongo medicalHistoryMongoItem : MedicalHistoryMongoBusiness.findAll()){

	    	listHistories = listHistories + "\nUser inserted: " + medicalHistoryMongoItem.patientId;
	    }
	    
	    return ok(index.render(listHistories));

    }
    public Result getPatientHistory(Integer id){

        if(id == null){
            return badRequest("Invalid input parameters");
        }

        MedicalHistoryMongo history = MedicalHistoryMongoBusiness.findByPatientId(id);
        
        return ok(Json.toJson(history));
    }
}
