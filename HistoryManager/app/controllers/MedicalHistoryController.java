package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import persistence.IMedicalHistoryDao;
import persistence.impl.MedicalHistoryDaoImpl;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import com.avaje.ebean.Model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Medical history controller for non emergency medical histories requests
 *
 * @author Sebastian Gamba Pinilla
 */
public class MedicalHistoryController extends Controller {

    final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    private IMedicalHistoryDao historyDao = new MedicalHistoryDaoImpl();

    /**
     * Obtains the medical history using patient id as key
     * @return the Medical history as JSON
     */
    public  Result getPatientHistory(Integer id){

        if(id == null){
            return badRequest("Invalid input parameters");
        }

        final MedicalHistory medicalHistory = historyDao.obtainMedicalHistoryByPatientId(id);
        if(medicalHistory == null){
            return noContent();
        }

        return ok(Json.toJson(medicalHistory));
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

    /**
     * Stores a patient in memory database and postgres
     * @return
     */
    public Result savePatient(){

        MedicalHistory medicalHistory = Json.fromJson(request().body().asJson(), MedicalHistory.class);
        
        Patient patient = Patient.find.byId(medicalHistory.getPatient().getId());

        if (patient == null) {
            patient = medicalHistory.getPatient();
        }

        List<Pathology> patientPathologies = medicalHistory.getPathologies();
        List<Allergy> patientAllergies = medicalHistory.getAllergies();
        List<MedicalProcedure> patientMedicalProcedures = medicalHistory.getMedicalProcedures();
        List<DiagnosticImage> patientDiagnosticImages = medicalHistory.getDiagnosticImages();
        
        patient.setPathologies(patientPathologies);
        patient.setAllergies(patientAllergies);
        patient.setMedicalProcedures(patientMedicalProcedures);
        patient.setDiagnosticImages(patientDiagnosticImages);

        EbeanServer defaultServer = Ebean.getServer("default");
        defaultServer.beginTransaction();
        defaultServer.save(patient);
        defaultServer.commitTransaction();

        EbeanServer secondary = Ebean.getServer("fast");
        secondary.beginTransaction(); 
        secondary.save(patient);
        secondary.commitTransaction(); 
        
        
        return ok("History inserted, patient: " + patient.getName());

    }

    private Patient buildPatientFromJsonNode(JsonNode jsonNode){
        try {
            return new Patient(jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null,
                    jsonNode.get("name") != null ? jsonNode.get("name").textValue() : null,
                    jsonNode.get("login") != null ? jsonNode.get("login").textValue() : null,
                    jsonNode.get("birthDate") != null ? FORMATTER.parse(jsonNode.get("birthDate").textValue()) : null,
                    jsonNode.get("bloodType") != null ? jsonNode.get("bloodType").textValue() : null,
                    jsonNode.get("heightCm") != null ? jsonNode.get("heightCm").asInt() : null,
                    jsonNode.get("weightGr") != null ? jsonNode.get("weightGr").asInt() : null,
                    jsonNode.get("active") != null ? Boolean.getBoolean(jsonNode.get("active").textValue()) : false);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    private Allergy buildAllergyFromJsonNode(JsonNode jsonNode){
        return new Allergy(jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null,
                jsonNode.get("name") != null ? jsonNode.get("name").asText() : null);
    }

    private Pathology buildPathologyFromJsonNode(JsonNode jsonNode){
        return new Pathology(jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null,
                jsonNode.get("name") != null ? jsonNode.get("name").asText() : null,
                jsonNode.get("triage") != null ? jsonNode.get("triage").asInt() : null);
    }

    private MedicalProcedure buildMedicalProcedureFromJsonNode(JsonNode jsonNode){
        try {
            return new MedicalProcedure(jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null,
                    jsonNode.get("name") != null ? jsonNode.get("name").asText() : null,
                    jsonNode.get("date") != null ? FORMATTER.parse(jsonNode.get("date").textValue()) : null);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    private DiagnosticImage buildDiagnosticImageFromJsonNode(JsonNode jsonNode){
        try {
            return new DiagnosticImage(jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null,
                    jsonNode.get("name") != null ? jsonNode.get("name").asText() : null,
                    jsonNode.get("type") != null ? jsonNode.get("type").asText() : null,
                    jsonNode.get("date") != null ? FORMATTER.parse(jsonNode.get("date").textValue()) : null);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }
}