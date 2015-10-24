package models.business;

import com.avaje.ebean.Model;
import models.Patient;
import java.util.List;

/**
 * Created by Ger on 11/10/2015.
 */
public class PatientBusiness {

    public static List<Patient> findAll() {
        List<Patient> patients = Patient.find.all();
        return patients;
    }

}



    