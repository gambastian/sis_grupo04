package models.business;

import com.avaje.ebean.Model;
import models.Allergy;
import java.util.List;

/**
 * Created by Ger on 11/10/2015.
 */
public class AllergyBusiness {

    public static List<Allergy> findAll() {
        List<Allergy> allergies = Allergy.find.all();
        return allergies;
    }

}



    