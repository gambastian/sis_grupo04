package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.mvc.*;
import models.mongo.business.AllergyMongoBusiness;
import models.mongo.AllergyMongo;
import views.html.*;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;


public class Application extends Controller {

    /*public Result index() {
        return ok(index.render("Hola mundo."));
    }


    public Result sayHello(){
        return ok(index.render("Hola Mundo"));
    }*/
    public Result viewAllergies() {
        
        Iterable<AllergyMongo> allergiesIterable = AllergyMongoBusiness.findAll();
        List<AllergyMongo> allergies = Lists.newArrayList(allergiesIterable);
        
        return ok(index.render(allergies));

    }
}
