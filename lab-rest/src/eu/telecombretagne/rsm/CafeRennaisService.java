package eu.telecombretagne.rsm;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;


@Path ("/caferennais") 
public class CafeRennaisService {
	
	
	@Path ("/hello")
	@GET
	@Produces("text/html")
	public Response sayHello(@QueryParam("myname") String myname){ 
		
		return Response.status(200).entity("hello, " + myname).build();}
		

	
}
