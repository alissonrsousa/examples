package br.com.digicade.geosite.geoestatistica.service.impl;

import java.util.List;

import com.google.inject.Inject;

import br.com.digicade.geosite.geoestatistica.dao.EstadoDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Estado;
import br.com.digicade.geosite.geoestatistica.service.EstadoService;

public class EstadoServiceImpl implements EstadoService{

	@Inject
	private EstadoDAO dao;
	
	@Override
	public List<Estado> findAll() {
		return dao.findAll();
	}

	@Override
	public Estado findByIdWithMunicipios(Long id) {
		return dao.findByIdWithMunicipios(id);
	}

}
