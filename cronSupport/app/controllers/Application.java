package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.mvc.*;
import models.mongo.business.DocumentBusiness;
import models.mongo.Document;
import views.html.*;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;
import java.net.*;
import java.io.*;
import play.Logger;

import javax.inject.Inject;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Application extends Controller {

    @Inject WSClient ws;
    
    /*public Result index() {
        return ok(index.render("Hola mundo."));
    }


    public Result sayHello(){
        return ok(index.render("Hola Mundo"));
    }*/
    public Result viewDocuments() {
        


        Iterable<Document> documentsIterable = DocumentBusiness.findAll();
        List<Document> documents = Lists.newArrayList(documentsIterable);
        
        return ok(index.render(documents));

    }


    public Result checkAndRestore() {
        
        Iterable<Document> documentsIterable = DocumentBusiness.findAll();
        List<Document> documents = Lists.newArrayList(documentsIterable);

        if (documents.size() > 0) {

            boolean resp = pingFunc("http://aascalabilitymh-1670752154.us-east-1.elb.amazonaws.com/index.html", 500);
            if (resp){
                for(Document document : documents){
                    //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode json = mapper.readTree(document.content);

                        ws.url("http://aascalabilitymh-1670752154.us-east-1.elb.amazonaws.com/patient").post(json);

                        System.out.println("Elemento restaurado ID: " + document.id.toString());
                        System.out.println("Elemento restaurado ID: " + document.content);
                        //DocumentBusiness.remove(document.id);
                    } catch (Exception ex) {
                        System.out.println("Problema devolviendo la información");
                    } finally {
                        System.out.println("ok");
                    }

                }
                System.out.println("Habían datos en la DB de soporte, y ya se han restaurado");
                return ok(ping.render("Habían datos en la DB de soporte, y ya se han restaurado"));
            }else{
                System.out.println("Hay datos en la DB de soporte, pero el balanceador sigue caído");
                return ok(ping.render("Hay datos en la DB de soporte, pero el balanceador sigue caído"));
            }
        }else{
            System.out.println("No hay datos en la DB de soporte");
            return ok(ping.render("No hay datos en la DB de soporte"));
        }

        

        

    }

    public Result pingTest() {
        System.out.println("hola");
            
        boolean resp = pingFunc("http://aascalabilitymh-1670752154.us-east-1.elb.amazonaws.com/index.html", 500);
        if (resp){
            return ok(ping.render("El balanceador está activo")); 
        } else{
            return ok(ping.render("El balanceador está abajo"));
        }
    }

    public static boolean pingFunc(String url, int timeout) {
        // Otherwise an exception may be thrown on invalid SSL certificates:
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            Logger.info(Integer.toString(responseCode));
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException exception) {
            return false;
        }
    }
}
