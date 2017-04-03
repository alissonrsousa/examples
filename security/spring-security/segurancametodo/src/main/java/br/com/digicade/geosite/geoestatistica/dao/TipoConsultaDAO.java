package br.com.digicade.geosite.geoestatistica.dao;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.dao.impl.TipoConsultaDAOImpl;
import br.com.digicade.geosite.geoestatistica.model.entity.TipoConsulta;

import com.google.inject.ImplementedBy;

@ImplementedBy(TipoConsultaDAOImpl.class)
public interface TipoConsultaDAO {
	List<TipoConsulta> findAll();
	TipoConsulta findByIdWithAtributos(Long id);
}
