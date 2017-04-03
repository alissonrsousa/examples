package br.com.digicade.geosite.geoestatistica.service;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioGerenciaEspacializacaoVO;
import br.com.digicade.geosite.geoestatistica.service.impl.EstatisticaServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(EstatisticaServiceImpl.class)
public interface EstatisticaService {
	List<LinhaRelatorioEstatisticaVO> getEstatisticas(FiltroEstatisticaVO filtro);
	StringBuilder montaQueryEstatisticas(FiltroEstatisticaVO filtro, List<Object> parameters);
	StringBuilder montaQueryGerenciaEspacializacao(FiltroEstatisticaVO filtro, List<Object> parameters);
	List<String> getColunasEstatisticasDetalhado(Long idAtributo);
	List<LinhaRelatorioDetalhadoVO> getEstatisticasDetalhado(FiltroEstatisticaDetalhadoVO filtro);
	List<LinhaRelatorioGerenciaEspacializacaoVO> getEstatisticasGerenciaEspacializacao(FiltroEstatisticaVO filtro);
}
