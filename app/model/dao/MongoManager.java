package model.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.jongo.Jongo;

/**
 * Created by ger on 11/10/15.
 */
public class MongoManager {

    private static MongoClient mongoClient;
    public static Jongo jongo;
    static {
        try{
        	String mongoUri = System.getenv().get("MONGOLAB_URI");
        	MongoClientURI mongoClientUri = new MongoClientURI(mongoUri);
            mongoClient = new MongoClient(new MongoClientURI(mongoUri));
            jongo = new Jongo(mongoClient.getDB(mongoClientUri.getDatabase()));
            System.out.println("DB:" + mongoClientUri.getDatabase());
            System.out.println("Created jongo" + jongo);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR" + e);
        }
    }


}
