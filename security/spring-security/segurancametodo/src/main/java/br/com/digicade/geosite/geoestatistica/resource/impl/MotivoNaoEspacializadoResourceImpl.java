package br.com.digicade.geosite.geoestatistica.resource.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.MotivoNaoEspacializado;
import br.com.digicade.geosite.geoestatistica.model.vo.MotivoNaoEspacializadoVO;
import br.com.digicade.geosite.geoestatistica.resource.MotivoNaoEspacializadoResource;
import br.com.digicade.geosite.geoestatistica.service.MotivoNaoEspacializadoService;
import br.com.digicade.geosite.geoestatistica.util.JPAUtil;

import com.google.inject.Inject;

public class MotivoNaoEspacializadoResourceImpl implements MotivoNaoEspacializadoResource {
	
	@Inject
	private MotivoNaoEspacializadoService service;
	
	@Inject
	private JPAUtil jpaUtil;	
	
	public List<MotivoNaoEspacializadoVO> findAll() throws Exception {
		List<MotivoNaoEspacializado> lista = service.findAll();
		return this.convertToVO(lista);
	}	

	
	private List<MotivoNaoEspacializadoVO> convertToVO(List<MotivoNaoEspacializado> lista) {
		List<MotivoNaoEspacializadoVO> listaVO = new ArrayList<MotivoNaoEspacializadoVO>();
		for(MotivoNaoEspacializado entidade: lista){
			MotivoNaoEspacializadoVO vo = new MotivoNaoEspacializadoVO();
			vo.setId(entidade.getId());
			vo.setDescricao(entidade.getDescricao());
			listaVO.add(vo);
		}
		return listaVO;
	}	

}
