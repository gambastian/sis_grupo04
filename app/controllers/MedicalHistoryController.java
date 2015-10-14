package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Allergy;
import models.MedicalHistory;
import models.Patient;
import persistence.IMedicalHistoryDao;
import persistence.impl.MedicalHistoryDaoImpl;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Medical history controller
 *
 * @author Sebastian Gamba Pinilla
 */
public class MedicalHistoryController extends Controller {

    private IMedicalHistoryDao historyDao = new MedicalHistoryDaoImpl();
    /**
     * Obtains the medical history using patient id as key
     * @return the Medical history as JSON
     */
    public  Result getPatientHistory(Integer id){

        if(id == null){
            return badRequest("Invalid input parameters");
        }

        // TODO :: traer el batch segun el id de paciente, convertir ese batch en un objeto MedicalHistory
        MedicalHistory history = new MedicalHistory();
        final Patient dummyPatient = Patient.findH2.byId(1);
        history.setPatient(dummyPatient);

        final MedicalHistory inMemoryHistory = historyDao.obtainMedicalHistoryByPatientId(history.getPatient().getId());

        addInMemoryInformation(history, inMemoryHistory);

        return ok(Json.toJson(history));
    }

    /**
     * Adds the information in memory to the batch history
     *
     * @param patientMedicalHistory
     * @param inMemoryMedicalHistory
     */
    private void addInMemoryInformation(MedicalHistory patientMedicalHistory, MedicalHistory inMemoryMedicalHistory){
        patientMedicalHistory.getAllergies().addAll(inMemoryMedicalHistory.getAllergies());
        patientMedicalHistory.getDiagnosticImages().addAll(inMemoryMedicalHistory.getDiagnosticImages());
        patientMedicalHistory.getMedicalProcedures().addAll(inMemoryMedicalHistory.getMedicalProcedures());
        patientMedicalHistory.getPathologies().addAll(inMemoryMedicalHistory.getPathologies());
    }

    /**
     * Fills H2 database with in memory data
     * @return
     */
    public Result fillDatabase(){
        final Patient dummyPatient = new Patient(1,"Anthony Stark","ironman",new Date(),"BN",177,82000,true);
        dummyPatient.save();


        Allergy allergy = new Allergy();
        allergy.setId(1);
        allergy.setName("dust");
        allergy.getPatients().add(dummyPatient);
        allergy.save();

        return ok("Database filled");
    }
}