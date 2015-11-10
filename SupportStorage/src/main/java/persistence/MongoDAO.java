package persistence;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import model.MongoDocument;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * Conection Class to MongoDb
 * @author Sebastian Gamba Pinilla
 */
public class MongoDAO implements IMongo{
	private final String HOST = "localhost";
	private final Integer PORT = 27017;

	@Override
	public void storeDocument(MongoDocument document) throws Exception {
		MongoClient mongo;
		try {
			mongo = new MongoClient( HOST, PORT);
			DB db = mongo.getDB("supportStorageDB");
			DBCollection table = db.getCollection("document");

			BasicDBObject mongoDocument = new BasicDBObject();
			mongoDocument.put("action", document.getAction().toString());
			mongoDocument.put("content", document.getContent());
			table.insert(mongoDocument);

		} catch (UnknownHostException e) {
			throw new Exception("MongoDB host: " + HOST + " is not known");
		}

	}

	@Override
	public List<MongoDocument> getAllDocuments() throws Exception {
		MongoClient mongo;
		try {
			mongo = new MongoClient( HOST, PORT);
			DB db = mongo.getDB("supportStorageDB");
			DBCollection table = db.getCollection("document");

			DBCursor cursor = table.find();

			if(cursor != null){
				List<MongoDocument> documents = new ArrayList<>();
				while (cursor.hasNext()) {
					documents.add(new MongoDocument((String) cursor.next().get(
							"action"), (String) cursor.next().get("content")));

				}
				return documents;
			} else {
				return null;
			}

		} catch (UnknownHostException e) {
			throw new Exception("MongoDB host: " + HOST + " is not known");
		}
	}

}
