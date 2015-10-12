package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import persistence.IMedicalHistoryDao;
import persistence.impl.MedicalHistoryDaoImpl;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Medical history controller
 *
 * @author Sebastian Gamba Pinilla
 */
public class MedicalHistoryController extends Controller {

    private IMedicalHistoryDao historyDao = new MedicalHistoryDaoImpl();
    /**
     * Obtains the medical history using patient id as key
     * @return the Medical history as JSON
     */
    public Result getPatientHistory(){
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json input");
        } else {
            Integer patientId = json.findPath("id").intValue();
            if(patientId == null){
                return badRequest("Invalid input parameters");
            }
            return null;
        }
    }
}
