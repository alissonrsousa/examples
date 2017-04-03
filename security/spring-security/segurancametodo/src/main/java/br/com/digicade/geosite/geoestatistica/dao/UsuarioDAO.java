package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.commons.base.dao.DAO;
import br.com.digicade.geosite.geoestatistica.dao.impl.UsuarioDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.entity.Usuario;

import com.google.inject.ImplementedBy;

@ImplementedBy(UsuarioDAOImpl.class)
public interface UsuarioDAO extends DAO {
	List<Usuario> findAll() throws Exception;
	List<Usuario> findByStatus(String status) throws Exception;
}
