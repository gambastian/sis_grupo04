package controllers;

import model.*;
import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;
import model.business.AllergyBusiness;

/**
 * Created by Ger on 10/12/15.
 */

public class ScheduleToMongo extends Controller {

    public Result execute() {

    	String name;
	    String listAllergies = "";
	    
	    List<Allergy> allAllergies = AllergyBusiness.findAll();


	    for(Allergy a : allAllergies){
	        name = a.getName();
	        listAllergies = listAllergies + ", " + name;
	    }
		return ok(index.render(listAllergies));
    }
}
