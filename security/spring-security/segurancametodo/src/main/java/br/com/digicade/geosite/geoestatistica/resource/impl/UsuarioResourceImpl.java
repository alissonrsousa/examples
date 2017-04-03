package br.com.digicade.geosite.geoestatistica.resource.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.digicade.geosite.geoestatistica.model.entity.Usuario;
import br.com.digicade.geosite.geoestatistica.model.vo.UsuarioVO;
import br.com.digicade.geosite.geoestatistica.resource.UsuarioResource;
import br.com.digicade.geosite.geoestatistica.service.UsuarioService;

import com.google.inject.Inject;

public class UsuarioResourceImpl implements UsuarioResource {
	
	@Inject
	private UsuarioService service;

	public List<UsuarioVO> findAll() throws Exception {
		List<Usuario> lista = service.findAll();
		return this.convertToVO(lista);
	}

	public List<UsuarioVO> findByStatus(String status) throws Exception {
		List<Usuario> lista = service.findByStatus(status);
		return this.convertToVO(lista);
	}
	
	private List<UsuarioVO> convertToVO(List<Usuario> lista) {
		List<UsuarioVO> listaVO = new ArrayList<UsuarioVO>();
		for(Usuario entidade: lista){
			UsuarioVO vo = new UsuarioVO();
			vo.setIdUsuario(entidade.getId());
			vo.setNome(entidade.getNome());
			vo.setStatus(entidade.getStatus());
			listaVO.add(vo);
		}
		return listaVO;
	}	

}
