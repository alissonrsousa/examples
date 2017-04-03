package br.com.digicade.geosite.geoestatistica.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.digicade.geosite.geoestatistica.model.vo.EstadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LocalidadeVO;
import br.com.digicade.geosite.geoestatistica.model.vo.MunicipioVO;

	@Path(value = "/localidade")
	@Produces("application/json; charset=UTF-8")
	public interface LocalidadeResource {
		
		@POST
		@Path(value = "/findEstados")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<EstadoVO> findEstados() throws Exception;
		
		@POST
		@Path(value = "/findMunicipiosByEstado")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<MunicipioVO> findMunicipiosByEstado(EstadoVO vo) throws Exception;	
		
		@POST
		@Path(value = "/findLocalidadesByMunicipio")
		@Consumes(MediaType.APPLICATION_JSON)
		public List<LocalidadeVO> findLocalidadesByMunicipio(MunicipioVO vo) throws Exception;			
		
	}
