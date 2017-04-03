package br.com.digicade.geosite.geoestatistica.service;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.Usuario;
import br.com.digicade.geosite.geoestatistica.service.impl.UsuarioServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(UsuarioServiceImpl.class)
public interface UsuarioService {
	List<Usuario> findAll() throws Exception;
	List<Usuario> findByStatus(String status) throws Exception;
}
