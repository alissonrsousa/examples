package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.commons.base.dao.DAO;
import br.com.digicade.geosite.geoestatistica.dao.impl.MunicipioDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;

import com.google.inject.ImplementedBy;

@ImplementedBy(MunicipioDAOImpl.class)
public interface MunicipioDAO extends DAO {
	Municipio findByIdWithLocalidades(Long id);
	List<Municipio> findByUF(String uf);
}
