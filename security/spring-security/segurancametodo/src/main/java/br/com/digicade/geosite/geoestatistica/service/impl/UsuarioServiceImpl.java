package br.com.digicade.geosite.geoestatistica.service.impl;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.UsuarioDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Usuario;
import br.com.digicade.geosite.geoestatistica.service.UsuarioService;

import com.google.inject.Inject;

public class UsuarioServiceImpl implements UsuarioService{

	@Inject
	private UsuarioDAO dao;

	@Override
	public List<Usuario> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public List<Usuario> findByStatus(String status) throws Exception {
		return dao.findByStatus(status);
	}
	

}
