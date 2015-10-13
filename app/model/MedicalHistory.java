package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEBASTIAN on 12/10/2015.
 */
public class MedicalHistory implements Serializable {

    private Patient patient;
    private List<Pathology> pathologies;
    private List<Allergy> allergies;
    private List<MedicalProcedure> medicalProcedures;
    private List<DiagnosticImage> diagnosticImages;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Pathology> getPathologies() {
        if(pathologies == null){
            pathologies = new ArrayList<>();
        }
        return pathologies;
    }

    public void setPathologies(List<Pathology> pathologies) {
        this.pathologies = pathologies;
    }

    public List<Allergy> getAllergies() {
        if(allergies == null){
            allergies = new ArrayList<>();
        }
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public List<MedicalProcedure> getMedicalProcedures() {
        if(medicalProcedures == null){
            medicalProcedures = new ArrayList<>();
        }
        return medicalProcedures;
    }

    public void setMedicalProcedures(List<MedicalProcedure> medicalProcedures) {
        this.medicalProcedures = medicalProcedures;
    }

    public List<DiagnosticImage> getDiagnosticImages() {
        if(diagnosticImages == null){
            diagnosticImages = new ArrayList<>();
        }
        return diagnosticImages;
    }

    public void setDiagnosticImages(List<DiagnosticImage> diagnosticImages) {
        this.diagnosticImages = diagnosticImages;
    }
}
