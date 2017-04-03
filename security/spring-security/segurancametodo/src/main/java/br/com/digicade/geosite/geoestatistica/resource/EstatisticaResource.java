package br.com.digicade.geosite.geoestatistica.resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;

import org.springframework.security.access.annotation.Secured;

import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;

	@Path(value = "/estatistica")
	@Produces("application/json; charset=UTF-8")
	public interface EstatisticaResource {
		
		@POST
		@Path(value = "/getEstatisticas")
		@Consumes(MediaType.APPLICATION_JSON)
		@Secured({"ROLE_USER","ROLE_ADMIN"})
		public JSONArray getEstatisticas(FiltroEstatisticaVO filtro) throws Exception;	
		
		@GET
		@Path("/exportarExcel")
		@Produces({"application/vnd.ms-excel"})
		@Secured("ROLE_ADMIN")
		public Response exportarExcel(@QueryParam("idTipoConsulta") Integer idTipoConsulta,
									  @QueryParam("listaAtributos") String listaAtributos,
									  @QueryParam("estado") String estado,
							   		  @QueryParam("idMunicipio") Long idMunicipio,
							   		  @QueryParam("idLocalidade") Long idLocalidade,
							   		  @QueryParam("idUsuario") Long idUsuario,
							   		  @QueryParam("dataInicio") Date dataInicio,
							   		  @QueryParam("dataFim") Date dataFim,
							   		  @QueryParam("tipoExportacao") String tipoExportacao,
							   		  @QueryParam("localSelecionado") String localSelecionado,
							   		  @QueryParam("descricaoFiltro") String descricaoFiltro,
							   		  @QueryParam("downloadID") String downloadID) throws Exception;	
		
		@GET
		@Path("/exportarPDF")
		@Produces({"application/pdf"})
		public Response exportarPDF(@QueryParam("idTipoConsulta") Integer idTipoConsulta,
									  @QueryParam("listaAtributos") String listaAtributos,
									  @QueryParam("estado") String estado,
							   		  @QueryParam("idMunicipio") Long idMunicipio,
							   		  @QueryParam("idLocalidade") Long idLocalidade,
							   		  @QueryParam("idUsuario") Long idUsuario,
							   		  @QueryParam("dataInicio") Date dataInicio,
							   		  @QueryParam("dataFim") Date dataFim,
							   		  @QueryParam("tipoExportacao") String tipoExportacao,
							   		  @QueryParam("localSelecionado") String localSelecionado,
							   		  @QueryParam("descricaoFiltro") String descricaoFiltro,
							   		  @QueryParam("downloadID") String downloadID) throws Exception;	
		
		@GET
		@Path("/exportarExcelDetalhado")
		@Produces({"application/vnd.ms-excel"})
		public Response exportarExcelDetalhado(@QueryParam("indEspacializado") Integer indEspacializado,
											   @QueryParam("idAtributo") Long idAtributo,
											   @QueryParam("estado") String estado,
									   		   @QueryParam("idMunicipio") Long idMunicipio,
									   		   @QueryParam("idLocalidade") Long idLocalidade,
									   		   @QueryParam("idUsuario") Long idUsuario,
									   		   @QueryParam("dataInicio") Date dataInicio,
									   		   @QueryParam("dataFim") Date dataFim,
									   		   @QueryParam("tipoExportacao") String tipoExportacao,
									   		   @QueryParam("descricaoFiltro") String descricaoFiltro,
									   		   @QueryParam("downloadID") String downloadID) throws Exception;	
		
		@GET
		@Path("/exportarPDFDetalhado")
		@Produces({"application/pdf"})
		public Response exportarPDFDetalhado(@QueryParam("indEspacializado") Integer indEspacializado,
				   @QueryParam("idAtributo") Long idAtributo,
				   @QueryParam("estado") String estado,
		   		   @QueryParam("idMunicipio") Long idMunicipio,
		   		   @QueryParam("idLocalidade") Long idLocalidade,
		   		   @QueryParam("idUsuario") Long idUsuario,
		   		   @QueryParam("dataInicio") Date dataInicio,
		   		   @QueryParam("dataFim") Date dataFim,
		   		   @QueryParam("tipoExportacao") String tipoExportacao,
		   		   @QueryParam("descricaoFiltro") String descricaoFiltro,
		   		   @QueryParam("downloadID") String downloadID) throws Exception;				
		
	}
