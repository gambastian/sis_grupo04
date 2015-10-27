package persistence.impl;

import com.avaje.ebean.Model;
import models.*;
import persistence.IMedicalHistoryDao;

import java.util.List;

/**
 * Implementation class for IMedicalHistoryDao
 *
 * @author Sebastian Gamba Pinilla
 */
public class MedicalHistoryDaoImpl implements IMedicalHistoryDao {

    @Override
    public MedicalHistory obtainMedicalHistoryByPatientId(Integer patientId) {

        final Patient patient = Patient.find.byId(patientId);
        if(patient == null){
            return null;
        }
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setPatient(patient);
        //Allergies
        List<Allergy> allergies = Allergy.find.where().eq("patients.id", patientId).findList();
        medicalHistory.getAllergies().addAll(allergies);
        //Pathologies
        List<Pathology> pathologies = Pathology.find.where().eq("patients.id", patientId).findList();
        medicalHistory.getPathologies().addAll(pathologies);
        //Diagnostic images
        List<DiagnosticImage> diagnosticImages = DiagnosticImage.find.where().eq("patient.id", patientId).findList();
        medicalHistory.getDiagnosticImages().addAll(diagnosticImages);
        //MedicalHistory procedures
        List<MedicalProcedure> medicalProcedures = MedicalProcedure.find.where().eq("patient.id", patientId).findList();
        medicalHistory.getMedicalProcedures().addAll(medicalProcedures);

        return medicalHistory;
    }
}
