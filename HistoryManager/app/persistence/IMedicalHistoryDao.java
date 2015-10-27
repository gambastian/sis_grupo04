package persistence;

import models.MedicalHistory;
import models.Patient;

/**
 * Interface contract for medical history methods
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IMedicalHistoryDao {

    MedicalHistory obtainMedicalHistoryByPatientId(Integer patientId);
    void savePatientFast(Patient patient);
    void savePatientHistoric(Patient patient);
}
