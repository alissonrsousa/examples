package br.com.digicade.geosite.geoestatistica.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.digicade.geosite.geoestatistica.model.vo.MotivoNaoEspacializadoVO;

	@Path(value = "/motivoNaoEspacializado")
	@Produces("application/json; charset=UTF-8")
	public interface MotivoNaoEspacializadoResource {
		
		@POST
		@Path(value = "/findAll")
		@Consumes(MediaType.APPLICATION_JSON)
		List<MotivoNaoEspacializadoVO> findAll() throws Exception;
		
	}
