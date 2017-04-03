package br.com.digicade.geosite.geoestatistica.resource.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.AtributoEstatistica;
import br.com.digicade.geosite.geoestatistica.model.entity.TipoConsulta;
import br.com.digicade.geosite.geoestatistica.model.vo.AtributoEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.TipoConsultaVO;
import br.com.digicade.geosite.geoestatistica.resource.TipoConsultaResource;
import br.com.digicade.geosite.geoestatistica.service.TipoConsultaService;
import br.com.digicade.geosite.geoestatistica.util.JPAUtil;

import com.google.inject.Inject;

public class TipoConsultaResourceImpl implements TipoConsultaResource {
	
	@Inject
	private TipoConsultaService service;
	
	@Inject
	private JPAUtil jpaUtil;	
	
	public List<TipoConsultaVO> findAll() throws Exception {
		List<TipoConsulta> lista = service.findAll();
		return this.convertToVO(lista);
	}	

	public List<AtributoEstatisticaVO> findAtributosById(TipoConsultaVO vo) throws Exception {
		TipoConsulta tipoConsulta = service.findByIdWithAtributos(vo.getId());
		return this.convertToVO(tipoConsulta);
	}
	
	private List<TipoConsultaVO> convertToVO(List<TipoConsulta> lista) {
		List<TipoConsultaVO> listaVO = new ArrayList<TipoConsultaVO>();
		for(TipoConsulta entidade: lista){
			TipoConsultaVO vo = new TipoConsultaVO();
			vo.setId(entidade.getId());
			vo.setDescricao(entidade.getDescricao());
			listaVO.add(vo);
		}
		return listaVO;
	}	
	
	private List<AtributoEstatisticaVO> convertToVO(TipoConsulta tipoConsulta){
		List<AtributoEstatisticaVO> lista = new ArrayList<AtributoEstatisticaVO>();
		for(AtributoEstatistica entidade: tipoConsulta.getAtributos()){
			AtributoEstatisticaVO vo = new AtributoEstatisticaVO();
			vo.setId(entidade.getId());
			vo.setDescricao(entidade.getDescricao());
			lista.add(vo);
		}
		return lista;
	}

}
