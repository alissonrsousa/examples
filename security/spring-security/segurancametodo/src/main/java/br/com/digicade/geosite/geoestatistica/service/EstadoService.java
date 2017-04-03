package br.com.digicade.geosite.geoestatistica.service;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.Estado;
import br.com.digicade.geosite.geoestatistica.service.impl.EstadoServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(EstadoServiceImpl.class)
public interface EstadoService {
	List<Estado> findAll();
	Estado findByIdWithMunicipios(Long id);
}
