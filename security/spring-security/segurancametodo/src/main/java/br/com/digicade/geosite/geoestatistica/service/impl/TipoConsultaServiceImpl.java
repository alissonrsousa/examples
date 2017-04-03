package br.com.digicade.geosite.geoestatistica.service.impl;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.TipoConsultaDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.TipoConsulta;
import br.com.digicade.geosite.geoestatistica.service.TipoConsultaService;

import com.google.inject.Inject;

public class TipoConsultaServiceImpl implements TipoConsultaService {

	@Inject
	private TipoConsultaDAO dao;
	
	@Override
	public TipoConsulta findByIdWithAtributos(Long id) {
		return dao.findByIdWithAtributos(id);
	}

	@Override
	public List<TipoConsulta> findAll() {
		return dao.findAll();
	}

}
