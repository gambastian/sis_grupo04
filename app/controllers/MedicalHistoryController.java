package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import persistence.IMedicalHistoryDao;
import persistence.impl.MedicalHistoryDaoImpl;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        final Patient dummyPatient = Patient.findH2.byId(id);
        if(dummyPatient == null){
            return noContent();
        }
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

        //Patients
        final Patient ironmanPatient = new Patient(1,"Anthony Stark","ironman",new Date(),"BN",177,82000,true);
        final Patient batmanPatient = new Patient(2,"Bruce Wayne","batman",new Date(),"AN",187,85000,true);
        final Patient supermanPatient = new Patient(3,"Clark Kent","superman",new Date(),"ON",177,92000,true);
        final Patient thorPatient = new Patient(4,"Thor Odinson","mjolnir",new Date(),"OP",177,90000,true);
        final Patient sailorPatient = new Patient(5,"Sailor Moon","sebas",new Date(),"=)",177,90000,true);
        ironmanPatient.save();
        batmanPatient.save();
        supermanPatient.save();
        thorPatient.save();
        sailorPatient.save();
        
        //Allergies
        Allergy allergyDust = new Allergy(1,"dust");
        allergyDust.getPatients().add(ironmanPatient);
        allergyDust.getPatients().add(thorPatient);
        allergyDust.save();
        Allergy allergyKrypto = new Allergy(2,"kryptonite");
        allergyKrypto.getPatients().add(supermanPatient);
        allergyKrypto.save();

        //Pathologies
        Pathology pathologyHighPressure = new Pathology(1,"HighPresure",3);
        pathologyHighPressure.getPatients().add(ironmanPatient);
        pathologyHighPressure.getPatients().add(thorPatient);
        pathologyHighPressure.save();

        //Diagnostic images
        DiagnosticImage diagnosticImageBatman = new DiagnosticImage(1, "Xray back", batmanPatient, "XRAY", new Date());
        DiagnosticImage diagnosticImageBatman2 = new DiagnosticImage(2, "Xray leg", batmanPatient, "XRAY", new Date());
        DiagnosticImage diagnosticImageIronman = new DiagnosticImage(3, "ECG", ironmanPatient, "ECG", new Date());
        diagnosticImageBatman.save();
        diagnosticImageBatman2.save();
        diagnosticImageIronman.save();

        //Medical procedure
        MedicalProcedure medicalProcedureIronman = new MedicalProcedure(1, "Sharpnel removal",ironmanPatient,new Date());
        medicalProcedureIronman.save();

        return ok("Database filled");
    }

    public Result deleteAll(){
        List<MedicalProcedure> medicalProcedures = MedicalProcedure.findH2.all();
        for(MedicalProcedure medicalProcedure : medicalProcedures){
            medicalProcedure.delete();
        }

        List<DiagnosticImage> diagnosticImages = DiagnosticImage.findH2.all();
        for(DiagnosticImage diagnosticImage : diagnosticImages){
            diagnosticImage.delete();
        }

        List<Allergy> allergies = Allergy.findH2.all();
        for(Allergy allergy : allergies){
            allergy.setPatients(new ArrayList<>());
            allergy.save();
            allergy.delete();
        }

        List<Pathology> pathologies = Pathology.findH2.all();
        for(Pathology pathology : pathologies){
            pathology.setPatients(new ArrayList<>());
            pathology.save();
            pathology.delete();
        }

        List<Patient> patients = Patient.findH2.all();
        for(Patient patient : patients){
            patient.delete();
        }

        return ok("Data clean");
    }
}