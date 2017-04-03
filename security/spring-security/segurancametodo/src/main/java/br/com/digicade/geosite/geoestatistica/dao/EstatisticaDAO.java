package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.impl.EstatisticaDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioGerenciaEspacializacaoVO;

import com.google.inject.ImplementedBy;

@ImplementedBy(EstatisticaDAOImpl.class)
public interface EstatisticaDAO {
	List<LinhaRelatorioEstatisticaVO> getEstatisticas(FiltroEstatisticaVO filtro);
	StringBuilder montaQueryEstatisticas(FiltroEstatisticaVO filtro, List<Object> parameters);
	StringBuilder montaQueryGerenciaEspacializacao(FiltroEstatisticaVO filtro, List<Object> parameters);
	List<String> getColunasEstatisticasDetalhado(Long idAtributo);
	List<LinhaRelatorioDetalhadoVO> getEstatisticasDetalhado(FiltroEstatisticaDetalhadoVO filtro);
	List<LinhaRelatorioGerenciaEspacializacaoVO> getEstatisticasGerenciaEspacializacao(FiltroEstatisticaVO filtro);
}
