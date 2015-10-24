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

    public MedicalHistory obtainMedicalHistoryByPatientIdPostgres(Integer patientId) {

        MedicalHistory medicalHistoryPostgres = new MedicalHistory();
        // TODO ::  traer la informacion de la base de datos de memoria
        //Allergies
        List<Allergy> allergies = Allergy.find.where().eq("patients.id", patientId).findList();
        medicalHistoryPostgres.getAllergies().addAll(allergies);
        //Pathologies
        List<Pathology> pathologies = Pathology.find.where().eq("patients.id", patientId).findList();
        medicalHistoryPostgres.getPathologies().addAll(pathologies);
        //Diagnostic images
        List<DiagnosticImage> diagnosticImages = DiagnosticImage.find.where().eq("patient.id", patientId).findList();
        medicalHistoryPostgres.getDiagnosticImages().addAll(diagnosticImages);
        //medicalHistoryPostgres procedures
        List<MedicalProcedure> medicalProcedures = MedicalProcedure.find.where().eq("patient.id", patientId).findList();
        medicalHistoryPostgres.getMedicalProcedures().addAll(medicalProcedures);

        return medicalHistoryPostgres;
    }
}
