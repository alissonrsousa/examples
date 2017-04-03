package br.com.alissonrsousa.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

	@Path(value = "/teste")
	@Produces("application/json; charset=UTF-8")
	public interface TesteResource {
		
		@POST
		@Path(value = "/teste")
		public String teste() throws Exception;
	
		
	}
