package br.com.digicade.geosite.geoestatistica.resource.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.Estado;
import br.com.digicade.geosite.geoestatistica.model.entity.Localidade;
import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;
import br.com.digicade.geosite.geoestatistica.model.vo.EstadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LocalidadeVO;
import br.com.digicade.geosite.geoestatistica.model.vo.MunicipioVO;
import br.com.digicade.geosite.geoestatistica.resource.LocalidadeResource;
import br.com.digicade.geosite.geoestatistica.service.EstadoService;
import br.com.digicade.geosite.geoestatistica.service.MunicipioService;

import com.google.inject.Inject;

public class LocalidadeResourceImpl implements LocalidadeResource {
	
	@Inject
	private EstadoService estadoService;
	
	@Inject
	private MunicipioService municipioService;

	public List<EstadoVO> findEstados() throws Exception {
		List<Estado> lista = estadoService.findAll();
		return this.convertEstadosToVO(lista);
	}

	public List<MunicipioVO> findMunicipiosByEstado(EstadoVO vo) throws Exception {
		Estado estado = estadoService.findByIdWithMunicipios(vo.getId());
		return this.convertMunicipiosToVO(estado.getMunicipios());
	}

	public List<LocalidadeVO> findLocalidadesByMunicipio(MunicipioVO vo) throws Exception {
		Municipio municipio = municipioService.findByIdWithLocalidades(vo.getId());
		return this.convertLocalidadesToVO(municipio.getLocalidades());
	}
	
	private List<EstadoVO> convertEstadosToVO(List<Estado> lista) {
		List<EstadoVO> listaVO = new ArrayList<EstadoVO>();
		for(Estado entidade: lista){
			EstadoVO vo = new EstadoVO();
			vo.setId(entidade.getId());
			vo.setUf(entidade.getUf());
			vo.setNome(entidade.getNome());
			listaVO.add(vo);
		}
		return listaVO;
	}	
	
	private List<MunicipioVO> convertMunicipiosToVO(List<Municipio> lista) {
		List<MunicipioVO> listaVO = new ArrayList<MunicipioVO>();
		for(Municipio entidade: lista){
			MunicipioVO vo = new MunicipioVO();
			vo.setId(entidade.getId());
			vo.setNome(entidade.getNome());
			listaVO.add(vo);
		}
		return listaVO;
	}	
	
	private List<LocalidadeVO> convertLocalidadesToVO(List<Localidade> lista) {
		List<LocalidadeVO> listaVO = new ArrayList<LocalidadeVO>();
		for(Localidade entidade: lista){
			LocalidadeVO vo = new LocalidadeVO();
			vo.setId(entidade.getId());
			vo.setNome(entidade.getNome());
			listaVO.add(vo);
		}
		return listaVO;
	}	

}
