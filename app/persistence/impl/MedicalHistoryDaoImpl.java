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

        MedicalHistory medicalHistoryMemory = new MedicalHistory();
        // TODO ::  traer la informacion de la base de datos de memoria
        //Allergies
        List<Allergy> allergies = Allergy.findH2.where().eq("patients.id", patientId).findList();
        medicalHistoryMemory.getAllergies().addAll(allergies);
        //Pathologies
        List<Pathology> pathologies = Pathology.findH2.where().eq("patients.id", patientId).findList();
        medicalHistoryMemory.getPathologies().addAll(pathologies);
        //Diagnostic images
        List<DiagnosticImage> diagnosticImages = DiagnosticImage.findH2.where().eq("patient.id", patientId).findList();
        medicalHistoryMemory.getDiagnosticImages().addAll(diagnosticImages);
        //MedicalHistory procedures
        List<MedicalProcedure> medicalProcedures = MedicalProcedure.findH2.where().eq("patient.id", patientId).findList();
        medicalHistoryMemory.getMedicalProcedures().addAll(medicalProcedures);

        return medicalHistoryMemory;
    }
}
