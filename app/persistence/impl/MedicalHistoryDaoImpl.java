package persistence.impl;

import com.avaje.ebean.Model;
import models.MedicalHistory;
import persistence.IMedicalHistoryDao;

/**
 * Implementation class for IMedicalHistoryDao
 *
 * @author Sebastian Gamba Pinilla
 */
public class MedicalHistoryDaoImpl implements IMedicalHistoryDao {

    @Override
    public MedicalHistory obtainMedicalHistoryByPatientId(Integer patientId) {
        // TODO ::  traer la informacion de la base de datos de memoria
        return new MedicalHistory();
    }
}
