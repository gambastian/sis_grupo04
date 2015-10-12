package persistence;

/**
 * Interface contract for medical history methods
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IMedicalHistoryDao {

    void obtainMedicalHistoryByPatientId(Integer patientId);
}
