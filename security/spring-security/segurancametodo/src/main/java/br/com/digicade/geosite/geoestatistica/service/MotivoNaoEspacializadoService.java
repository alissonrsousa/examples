package br.com.digicade.geosite.geoestatistica.service;

import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.MotivoNaoEspacializado;
import br.com.digicade.geosite.geoestatistica.service.impl.MotivoNaoEspacializadoServiceImpl;

import com.google.inject.ImplementedBy;

@ImplementedBy(MotivoNaoEspacializadoServiceImpl.class)
public interface MotivoNaoEspacializadoService {
	List<MotivoNaoEspacializado> findAll();
}
