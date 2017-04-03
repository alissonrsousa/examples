package br.com.digicade.geosite.geoestatistica.service.impl;

import com.google.inject.Inject;

import br.com.digicade.geosite.geoestatistica.dao.MunicipioDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;
import br.com.digicade.geosite.geoestatistica.service.MunicipioService;

public class MunicipioServiceImpl implements MunicipioService {

	@Inject
	private MunicipioDAO dao;
	
	@Override
	public Municipio findByIdWithLocalidades(Long id) {
		return dao.findByIdWithLocalidades(id);
	}


}
