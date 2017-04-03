package br.com.digicade.geosite.geoestatistica.dao.impl;

import org.hibernate.Hibernate;

import com.wideplay.warp.persist.Transactional;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;
import br.com.digicade.geosite.geoestatistica.dao.TipoConsultaDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.TipoConsulta;

public class TipoConsultaDAOImpl extends BaseDAOImpl<TipoConsulta> implements TipoConsultaDAO {

	@Override
	@Transactional
	public TipoConsulta findByIdWithAtributos(Long id) {
		TipoConsulta tipoConsulta = findById(id);
		Hibernate.initialize(tipoConsulta.getAtributos());
		return tipoConsulta;
	}

}
