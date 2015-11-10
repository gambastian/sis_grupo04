package controllers;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persistence.IMongo;
import persistence.MongoDAO;
import model.MongoDocument;

/**
 * Controller class for support storage - SIS
 *
 * @author Sebastian Gamba Pinilla
 */
@Path("/")
public class SupportStorageController {

	private IMongo mongoService = new MongoDAO();

	@GET
	@Path("/hello")
	public Response responseMsg() {

		String output = "Hello Sebas ";

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/store")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response storeDocument(MongoDocument document) {

		try {
			mongoService.storeDocument(document);
			String result = "Document saved : " + document;
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/documents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDocuments() {
		try {
			List<MongoDocument> documents = mongoService.getAllDocuments();

			return Response.status(200).entity(documents).build();

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
