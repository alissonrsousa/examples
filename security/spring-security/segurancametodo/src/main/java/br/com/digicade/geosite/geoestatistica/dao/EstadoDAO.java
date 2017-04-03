package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.commons.base.dao.DAO;
import br.com.digicade.geosite.geoestatistica.dao.impl.EstadoDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.entity.Estado;

import com.google.inject.ImplementedBy;

@ImplementedBy(EstadoDAOImpl.class)
public interface EstadoDAO extends DAO {
	List<Estado> findAll();
	Estado findByIdWithMunicipios(Long id);
}
