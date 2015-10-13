package persistence;

import model.MedicalHistory;

/**
 * Interface contract for medical history methods
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IMedicalHistoryDao {

    MedicalHistory obtainMedicalHistoryByPatientId(Integer patientId);
}
