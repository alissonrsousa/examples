package br.com.digicade.geosite.geoestatistica.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.digicade.geosite.geoestatistica.model.vo.AtributoEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.TipoConsultaVO;

	@Path(value = "/tipoConsulta")
	@Produces("application/json; charset=UTF-8")
	public interface TipoConsultaResource {
		
		@POST
		@Path(value = "/findAll")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<TipoConsultaVO> findAll() throws Exception;
		
		@POST
		@Path(value = "/findAtributosById")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AtributoEstatisticaVO> findAtributosById(TipoConsultaVO vo) throws Exception;	
		
	}
