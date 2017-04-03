package br.com.digicade.geosite.geoestatistica.service;

import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;
import br.com.digicade.geosite.geoestatistica.service.impl.MunicipioServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(MunicipioServiceImpl.class)
public interface MunicipioService {
	Municipio findByIdWithLocalidades(Long id);
}
