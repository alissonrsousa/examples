package br.com.digicade.geosite.geoestatistica.service.impl;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.MotivoNaoEspacializadoDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.MotivoNaoEspacializado;
import br.com.digicade.geosite.geoestatistica.service.MotivoNaoEspacializadoService;

import com.google.inject.Inject;

public class MotivoNaoEspacializadoServiceImpl implements MotivoNaoEspacializadoService{

	@Inject
	private MotivoNaoEspacializadoDAO dao;
	
	@Override
	public List<MotivoNaoEspacializado> findAll() {
		return dao.findAll();
	}
}
