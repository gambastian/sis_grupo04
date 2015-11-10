package controllers;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.MongoDocument;

/**
 * Controller class for support storage - SIS
 *
 * @author Sebastian Gamba Pinilla
 */
@Path("/")
public class SupportStorageController {

	@GET
	@Path("/hello")
	public Response responseMsg() {

		String output = "Hello Sebas ";

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/store")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(MongoDocument document) {

		String result = "Document saved : " + document;
		return Response.status(201).entity(result).build();

	}

}
