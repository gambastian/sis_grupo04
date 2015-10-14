package persistence.impl;

import com.avaje.ebean.Model;
import models.Allergy;
import models.MedicalHistory;
import models.Patient;
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
        List<Allergy> allergies = Allergy.findH2.where().eq("patients.id", patientId).findList();
        medicalHistoryMemory.getAllergies().addAll(allergies);
        return medicalHistoryMemory;
    }
}
