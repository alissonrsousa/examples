package br.com.digicade.geosite.geoestatistica.service.impl;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.EstatisticaDAO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioGerenciaEspacializacaoVO;
import br.com.digicade.geosite.geoestatistica.service.EstatisticaService;

import com.google.inject.Inject;

public class EstatisticaServiceImpl implements EstatisticaService {

	@Inject
	private EstatisticaDAO dao;

	@Override
	public List<LinhaRelatorioEstatisticaVO> getEstatisticas(FiltroEstatisticaVO filtro) {
		return dao.getEstatisticas(filtro);
	}

	@Override
	public StringBuilder montaQueryEstatisticas(FiltroEstatisticaVO filtro, List<Object> parameters) {
		return dao.montaQueryEstatisticas(filtro, parameters);
	}
	
	@Override
	public StringBuilder montaQueryGerenciaEspacializacao(FiltroEstatisticaVO filtro, List<Object> parameters) {
		return dao.montaQueryGerenciaEspacializacao(filtro, parameters);
	}	

	@Override
	public List<String> getColunasEstatisticasDetalhado(Long idAtributo) {
		return dao.getColunasEstatisticasDetalhado(idAtributo);
	}

	@Override
	public List<LinhaRelatorioDetalhadoVO> getEstatisticasDetalhado(FiltroEstatisticaDetalhadoVO filtro) {
		return dao.getEstatisticasDetalhado(filtro);
	}

	@Override
	public List<LinhaRelatorioGerenciaEspacializacaoVO> getEstatisticasGerenciaEspacializacao(FiltroEstatisticaVO filtro) {
		return dao.getEstatisticasGerenciaEspacializacao(filtro);
	}

}
