package persistence;

import models.MedicalHistory;

/**
 * Interface contract for medical history methods
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IMedicalHistoryDao {

    MedicalHistory obtainMedicalHistoryByPatientId(Integer patientId);
    MedicalHistory obtainMedicalHistoryByPatientIdPostgres(Integer patientId);
}
