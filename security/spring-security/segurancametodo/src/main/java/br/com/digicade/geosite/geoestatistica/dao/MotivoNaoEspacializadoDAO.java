package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.commons.base.dao.DAO;
import br.com.digicade.geosite.geoestatistica.dao.impl.MotivoNaoEspacializadoDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.entity.MotivoNaoEspacializado;

import com.google.inject.ImplementedBy;

@ImplementedBy(MotivoNaoEspacializadoDAOImpl.class)
public interface MotivoNaoEspacializadoDAO extends DAO {
	List<MotivoNaoEspacializado> findAll();
}
