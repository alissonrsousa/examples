package br.com.digicade.geosite.geoestatistica.resource.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.json.JSONArray;

import org.springframework.security.access.prepost.PreAuthorize;

import br.com.digicade.geosite.geoestatistica.enums.TipoConsultaEnum;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.report.ColumnConfig;
import br.com.digicade.geosite.geoestatistica.report.DesignConfig;
import br.com.digicade.geosite.geoestatistica.report.DynamicReportDesign;
import br.com.digicade.geosite.geoestatistica.report.ReportManager;
import br.com.digicade.geosite.geoestatistica.resource.EstatisticaResource;
import br.com.digicade.geosite.geoestatistica.service.EstatisticaService;
import br.com.digicade.geosite.geoestatistica.util.Constantes;
import br.com.digicade.geosite.geoestatistica.util.JPAUtil;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

public class EstatisticaResourceImpl implements EstatisticaResource {
	
	@Inject
	private EstatisticaService service;
	
	@Inject
	private ServletContext context; 
	
	@Inject
	private JPAUtil jpaUtil;	
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmm");	
	
	@Transactional
	public JSONArray getEstatisticas(FiltroEstatisticaVO filtro) throws Exception {
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder sql;
		if(filtro.getIdTipoConsulta() != TipoConsultaEnum.GERENCIA_ESPACIALIZACAO.getValor()){
			sql = service.montaQueryEstatisticas(filtro, parameters);
		}
		else{
			sql = service.montaQueryGerenciaEspacializacao(filtro, parameters);
		}		
		return jpaUtil.getJsonResultList(sql.toString(), parameters);
	}

	@Override
	public Response exportarExcel(Integer idTipoConsulta,
								  String listaAtributos,
								  String estado,
								  Long idMunicipio,
								  Long idLocalidade,
								  Long idUsuario,
								  Date dataInicio,
								  Date dataFim,
								  String tipoExportacao,
								  String localSelecionado,
								  String descricaoFiltro,
								  String downloadID) throws Exception {
		
		NewCookie cookie = new NewCookie("downloadID", downloadID, "/", Constantes.DOMINIO_DIGICADE, 1, "id Download", 21600, false);
		
		FiltroEstatisticaVO filtro = new FiltroEstatisticaVO();
		filtro.setIdTipoConsulta(idTipoConsulta);
		filtro.setListaAtributos(listaAtributos);
		filtro.setEstado(estado);
		filtro.setIdMunicipio(idMunicipio);
		filtro.setIdLocalidade(idLocalidade);
		filtro.setIdUsuario(idUsuario);
		filtro.setDataInicio(dataInicio);
		filtro.setDataFim(dataFim);
		filtro.setTipoExportacao(tipoExportacao);
		filtro.setLocalSelecionado(localSelecionado);
		filtro.setDescricaoFiltro(descricaoFiltro);
		
		ResponseBuilder response = Response.ok((Object) this.getFile(filtro)).cookie(cookie);

		response.header("Content-Disposition", "attachment; filename=Estatisticas " + dateFormat.format(new Date())+ ".xls");
		return response.build();		
	}
	
	@Override
	public Response exportarPDF(Integer idTipoConsulta,
								String listaAtributos,
								String estado,
								Long idMunicipio,
								Long idLocalidade,
								Long idUsuario,
								Date dataInicio,
								Date dataFim,
								String tipoExportacao,
								String localSelecionado,
								String descricaoFiltro,
								String downloadID) throws Exception {
		
		NewCookie cookie = new NewCookie("downloadID", downloadID, "/", Constantes.DOMINIO_DIGICADE, 1, "id Download", 21600, false);
		
		FiltroEstatisticaVO filtro = new FiltroEstatisticaVO();
		filtro.setIdTipoConsulta(idTipoConsulta);
		filtro.setListaAtributos(listaAtributos);
		filtro.setEstado(estado);
		filtro.setIdMunicipio(idMunicipio);
		filtro.setIdLocalidade(idLocalidade);
		filtro.setIdUsuario(idUsuario);
		filtro.setDataInicio(dataInicio);
		filtro.setDataFim(dataFim);
		filtro.setTipoExportacao(tipoExportacao);
		filtro.setLocalSelecionado(localSelecionado);
		filtro.setDescricaoFiltro(descricaoFiltro);
		
		ResponseBuilder response = Response.ok((Object) this.getFile(filtro)).cookie(cookie);

		response.header("Content-Disposition", "attachment; filename=Estatisticas " + dateFormat.format(new Date())+ ".pdf");
		return response.build();
	}		
	
	@Override
	public Response exportarExcelDetalhado(Integer indEspacializado,
										   Long idAtributo,
										   String estado,
										   Long idMunicipio,
										   Long idLocalidade,
										   Long idUsuario,
										   Date dataInicio,
										   Date dataFim,
										   String tipoExportacao,
										   String descricaoFiltro,
										   String downloadID) throws Exception {

		NewCookie cookie = new NewCookie("downloadID", downloadID, "/", Constantes.DOMINIO_DIGICADE, 1, "id Download", 21600, false);
		
		FiltroEstatisticaDetalhadoVO filtro = new FiltroEstatisticaDetalhadoVO();
		filtro.setIndEspacializado(indEspacializado);
		filtro.setIdAtributo(idAtributo);
		filtro.setEstado(estado);
		filtro.setIdMunicipio(idMunicipio);
		filtro.setIdLocalidade(idLocalidade);
		filtro.setIdUsuario(idUsuario);
		filtro.setDataInicio(dataInicio);
		filtro.setDataFim(dataFim);
		filtro.setTipoExportacao(tipoExportacao);
		filtro.setDescricaoFiltro(descricaoFiltro);
		
		ResponseBuilder response = Response.ok((Object) this.getFileDetalhado(filtro)).cookie(cookie);

		response.header("Content-Disposition", "attachment; filename=EstatisticasDetalhado " + dateFormat.format(new Date())+ ".xls");
		return response.build();		
	}

	@Override
	public Response exportarPDFDetalhado(Integer indEspacializado,
										 Long idAtributo,
										 String estado,
										 Long idMunicipio,
										 Long idLocalidade,
										 Long idUsuario,
										 Date dataInicio,
										 Date dataFim,
										 String tipoExportacao,
										 String descricaoFiltro,
										 String downloadID) throws Exception {

		NewCookie cookie = new NewCookie("downloadID", downloadID, "/", Constantes.DOMINIO_DIGICADE, 1, "id Download", 21600, false);
		
		FiltroEstatisticaDetalhadoVO filtro = new FiltroEstatisticaDetalhadoVO();
		filtro.setIndEspacializado(indEspacializado);
		filtro.setIdAtributo(idAtributo);
		filtro.setEstado(estado);
		filtro.setIdMunicipio(idMunicipio);
		filtro.setIdLocalidade(idLocalidade);
		filtro.setIdUsuario(idUsuario);
		filtro.setDataInicio(dataInicio);
		filtro.setDataFim(dataFim);
		filtro.setTipoExportacao(tipoExportacao);
		filtro.setDescricaoFiltro(descricaoFiltro);
		
		ResponseBuilder response = Response.ok((Object) this.getFileDetalhado(filtro)).cookie(cookie);

		response.header("Content-Disposition", "attachment; filename=EstatisticasDetalhado " + dateFormat.format(new Date())+ ".pdf");
		return response.build();		
	}
	
	private StreamingOutput getFile(final FiltroEstatisticaVO filtro) throws Exception{
		
		//resultset
		List records = new ArrayList<Object>();
		if(filtro.getIdTipoConsulta() != TipoConsultaEnum.GERENCIA_ESPACIALIZACAO.getValor()){
			records = service.getEstatisticas(filtro);
		}
		else{
			records = service.getEstatisticasGerenciaEspacializacao(filtro);
		}
		
		
		//colunas
		List<ColumnConfig> colunas = this.getColunas(TipoConsultaEnum.getByValor(filtro.getIdTipoConsulta()), filtro.getLocalSelecionado());
		
		//cria o template jasper
		DesignConfig config = new DesignConfig();
		config.setShowLogo(true);
		config.setTitulo(this.getTitulo(filtro.getIdTipoConsulta()));
		//config.setSubTitulo("Filtros: " + filtro.getDescricaoFiltro());
		config.setColunas(colunas);
		DynamicReportDesign design = new DynamicReportDesign(config);	
		design.setName(this.getTitulo(filtro.getIdTipoConsulta()));
		design.setLeftMargin(10);
		design.setOrientation(OrientationEnum.LANDSCAPE);
		design.setRightMargin(10);
		design.setTopMargin(10);
		design.setBottomMargin(10);		
		
		if("pdf".equalsIgnoreCase(filtro.getTipoExportacao())){
			Integer totalWidth = 0;
			for(ColumnConfig columnConfig : colunas) {
				totalWidth += columnConfig.getWidth();
			}
			if(totalWidth < 575){
				design.setPageWidth(595);
				design.setPageHeight(842);
			}
			else if(totalWidth < 842){
				design.setPageWidth(842);
				design.setPageHeight(595);
			}
			else{
				design.setPageWidth(totalWidth+20);
				design.setPageHeight(595);
			}
		}			
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		String path = context.getRealPath("resources/images/oi.jpg");
		parameters.put("IMG_LOGO_PATH", path);
		final ReportManager report = new ReportManager(design, records, parameters);
		
		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				try {
					if("excel".equalsIgnoreCase(filtro.getTipoExportacao())){
						output.write(report.getExcelFile());
					}
					else{
						output.write(report.getPdfFile());
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new WebApplicationException(e);
				}
			}
		};
	}

	private String getTitulo(Integer tipoConsulta) {
		switch (tipoConsulta) {
			case 1:
				return "Feições Gráficas - MUB";
				
			case 2:
				return "Espacialização de Dados";
				
			default:
				return "Gerência de Espacialização";
		}
	}

	private List<ColumnConfig> getColunas(TipoConsultaEnum tipoConsulta, String localSelecionado){
		
		List<ColumnConfig> colunas = new ArrayList<ColumnConfig>();
		
		switch (tipoConsulta.getValor()) {
			case 1:
				ColumnConfig coluna1 = new ColumnConfig();
				coluna1.setWidth(150);
				coluna1.setDataIndex("atributo");
				coluna1.setText("Feições Gráficas - MUB");
				coluna1.setValueClassName("java.lang.String");
				colunas.add(coluna1);
				
				ColumnConfig coluna2 = new ColumnConfig();
				coluna2.setWidth(100);
				coluna2.setDataIndex("quantidade");
				coluna2.setText(localSelecionado);
				coluna2.setValueClassName("java.lang.Long");
				colunas.add(coluna2);
				break;
				
			case 2:
				ColumnConfig coluna3 = new ColumnConfig();
				coluna3.setWidth(150);
				coluna3.setDataIndex("atributo");
				coluna3.setText("Espacialização de Dados");
				coluna3.setValueClassName("java.lang.String");
				colunas.add(coluna3);
				
				ColumnConfig coluna4 = new ColumnConfig();
				coluna4.setWidth(100);
				coluna4.setDataIndex("quantidadeEspacializado");
				coluna4.setText("Espacializado");
				coluna4.setValueClassName("java.lang.Long");
				colunas.add(coluna4);
				
				ColumnConfig coluna5 = new ColumnConfig();
				coluna5.setWidth(100);
				coluna5.setDataIndex("quantidadeNaoEspacializado");
				coluna5.setText("Não Espacializado");
				coluna5.setValueClassName("java.lang.Long");
				colunas.add(coluna5);
				
				ColumnConfig coluna6 = new ColumnConfig();
				coluna6.setWidth(100);
				coluna6.setDataIndex("porcentagemEspacializado");
				coluna6.setText("Porcentagem");
				coluna6.setValueClassName("java.lang.String");
				colunas.add(coluna6);				
				break;				
	
			case 3:
				ColumnConfig coluna7 = new ColumnConfig();
				coluna7.setWidth(150);
				coluna7.setDataIndex("motivo");
				coluna7.setText("Gerência de Espacialização");
				coluna7.setValueClassName("java.lang.String");
				colunas.add(coluna7);
				
				ColumnConfig coluna8 = new ColumnConfig();
				coluna8.setWidth(100);
				coluna8.setDataIndex("quantidadeLogradouro");
				coluna8.setText("Logradouro");
				coluna8.setValueClassName("java.lang.Long");
				colunas.add(coluna8);
				
				ColumnConfig coluna9 = new ColumnConfig();
				coluna9.setWidth(100);
				coluna9.setDataIndex("quantidadeEstacao");
				coluna9.setText("Estação");
				coluna9.setValueClassName("java.lang.Long");
				colunas.add(coluna9);
				
				ColumnConfig coluna10 = new ColumnConfig();
				coluna10.setWidth(100);
				coluna10.setDataIndex("quantidadeCaixaTerminal");
				coluna10.setText("Caixa Terminal");
				coluna10.setValueClassName("java.lang.Long");
				colunas.add(coluna10);		
				
				ColumnConfig coluna11 = new ColumnConfig();
				coluna11.setWidth(100);
				coluna11.setDataIndex("quantidadeSecaoServico");
				coluna11.setText("Seção Serviço");
				coluna11.setValueClassName("java.lang.Long");
				colunas.add(coluna11);					
				break;	
		}
		return colunas;
	}	
	
	@Transactional
	private StreamingOutput getFileDetalhado(final FiltroEstatisticaDetalhadoVO filtro) throws Exception{
		
		//resultset
		List<LinhaRelatorioDetalhadoVO> records = service.getEstatisticasDetalhado(filtro);
		
		//colunas
		List<String> nomesColunas = service.getColunasEstatisticasDetalhado(filtro.getIdAtributo());
		List<ColumnConfig> colunas = this.getColunas(nomesColunas);
		
		//cria o template jasper
		DesignConfig config = new DesignConfig();
		config.setShowLogo(true);
		config.setTitulo("Relatório Detalhado");
		//config.setSubTitulo("Filtros: " + filtro.getDescricaoFiltro());
		config.setColunas(colunas);
		DynamicReportDesign design = new DynamicReportDesign(config);	
		design.setName("Relatório Detalhado");
		design.setLeftMargin(10);
		design.setOrientation(OrientationEnum.LANDSCAPE);
		design.setRightMargin(10);
		design.setTopMargin(10);
		design.setBottomMargin(10);		
		
		if("pdf".equalsIgnoreCase(filtro.getTipoExportacao())){
			Integer totalWidth = 0;
			for(ColumnConfig columnConfig : colunas) {
				totalWidth += columnConfig.getWidth();
			}
			if(totalWidth < 575){
				design.setPageWidth(595);
				design.setPageHeight(842);
			}
			else if(totalWidth < 842){
				design.setPageWidth(842);
				design.setPageHeight(595);
			}
			else{
				design.setPageWidth(totalWidth+20);
				design.setPageHeight(595);
			}
		}			
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		String path = context.getRealPath("resources/images/oi.jpg");
		parameters.put("IMG_LOGO_PATH", path);
		final ReportManager report = new ReportManager(design, records, parameters);
		
		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				try {
					if("excel".equalsIgnoreCase(filtro.getTipoExportacao())){
						output.write(report.getExcelFile());
					}
					else{
						output.write(report.getPdfFile());
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new WebApplicationException(e);
				}
			}
		};
	}	
	
	private List<ColumnConfig> getColunas(List<String> nomesColunas){
		
		List<ColumnConfig> colunas = new ArrayList<ColumnConfig>();
		
		for(String nomeColuna: nomesColunas){
			String cabecalho = LinhaRelatorioDetalhadoVO.getCabecalhoColuna(nomeColuna);
			ColumnConfig coluna = new ColumnConfig();
			coluna.setWidth(150);
			coluna.setDataIndex(nomeColuna);
			coluna.setText(cabecalho);
			coluna.setValueClassName("java.lang.String");
			colunas.add(coluna);
		}
				
		return colunas;
	}	

}
