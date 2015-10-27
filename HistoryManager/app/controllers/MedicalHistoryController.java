package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import models.mongo.*;
import models.mongo.business.MedicalHistoryMongoBusiness;
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
 * Medical history controller
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

        MedicalHistoryMongo medicalHistoryMongo = MedicalHistoryMongoBusiness.findByPatientId(id);
        if(medicalHistoryMongo == null){
            return noContent();
        }

        MedicalHistory history = mapToMedicalHistory(medicalHistoryMongo);

        final MedicalHistory inMemoryHistory = historyDao.obtainMedicalHistoryByPatientId(history.getPatient().getId());

        addInMemoryInformation(history, inMemoryHistory);

        return ok(Json.toJson(history));
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
     * Maps a mongo medical history to a resource medical history
     * @param medicalHistoryMongo
     * @return
     */
    private MedicalHistory mapToMedicalHistory(MedicalHistoryMongo medicalHistoryMongo){
        MedicalHistory history = new MedicalHistory();

        final Patient patient = new Patient(medicalHistoryMongo.patient);
        history.setPatient(patient);

        if(medicalHistoryMongo.allergies != null){
            List<Allergy> allergies = new ArrayList<>();
            for(AllergyMongo allergyMongo : medicalHistoryMongo.allergies){
                allergies.add(new Allergy(allergyMongo));
            }
            history.setAllergies(allergies);
        }

        if(medicalHistoryMongo.pathologies != null){
            List<Pathology> pathologies = new ArrayList<>();
            for(PathologyMongo pathologyMongo : medicalHistoryMongo.pathologies){
                pathologies.add(new Pathology(pathologyMongo));
            }
            history.setPathologies(pathologies);
        }

        if(medicalHistoryMongo.diagnosticImages != null){
            List<DiagnosticImage> diagnosticImages = new ArrayList<>();
            for(DiagnosticImageMongo diagnosticImageMongo : medicalHistoryMongo.diagnosticImages){
                diagnosticImages.add(new DiagnosticImage(diagnosticImageMongo));
            }
            history.setDiagnosticImages(diagnosticImages);
        }

        if(medicalHistoryMongo.medicalProcedures != null){
            List<MedicalProcedure> medicalProcedures = new ArrayList<>();
            for(MedicalProcedureMongo medicalProcedureMongo : medicalHistoryMongo.medicalProcedures){
                medicalProcedures.add(new MedicalProcedure(medicalProcedureMongo));
            }
            history.setMedicalProcedures(medicalProcedures);
        }

        return history;
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

        /*EbeanServer defaultServer = Ebean.getServer("default");
        defaultServer.beginTransaction();
        defaultServer.save(patient);
        defaultServer.commitTransaction(); */

        EbeanServer secondary = Ebean.getServer("secundary");
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