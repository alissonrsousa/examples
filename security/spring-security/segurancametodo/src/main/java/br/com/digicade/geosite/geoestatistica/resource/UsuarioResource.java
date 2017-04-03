package br.com.digicade.geosite.geoestatistica.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.digicade.geosite.geoestatistica.model.vo.UsuarioVO;

	@Path(value = "/usuario")
	@Produces("application/json; charset=UTF-8")
	public interface UsuarioResource {
		
		@POST
		@Path(value = "/findAll")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<UsuarioVO> findAll() throws Exception;
		
		@POST
		@Path(value = "/findByStatus")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<UsuarioVO> findByStatus(String status) throws Exception;	
		
	}
