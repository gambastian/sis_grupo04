package persistence;

import java.util.List;

import model.MongoDocument;

/**
 * Contract for mongo DB operations
 *
 * @author Sebastian Gamba Pinilla
 */
public interface IMongo {

	/**
	 * Stores a {@link MongoDocument} in database containing the action to be
	 * done with the JSON (String) content
	 *
	 * @param document
	 */
	void storeDocument(MongoDocument document) throws Exception;

	/**
	 * Finds all documents stored in DB
	 * @return
	 * @throws Exception
	 */
	List<MongoDocument> getAllDocuments() throws Exception;
}
