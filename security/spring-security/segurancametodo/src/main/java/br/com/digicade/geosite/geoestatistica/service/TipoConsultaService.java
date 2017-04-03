package br.com.digicade.geosite.geoestatistica.service;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.TipoConsulta;
import br.com.digicade.geosite.geoestatistica.service.impl.TipoConsultaServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(TipoConsultaServiceImpl.class)
public interface TipoConsultaService {
	TipoConsulta findByIdWithAtributos(Long id);
	List<TipoConsulta> findAll();
}
