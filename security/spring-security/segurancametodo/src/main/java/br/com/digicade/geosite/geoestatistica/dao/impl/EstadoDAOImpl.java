package br.com.digicade.geosite.geoestatistica.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;
import br.com.digicade.geosite.geoestatistica.dao.EstadoDAO;
import br.com.digicade.geosite.geoestatistica.dao.MunicipioDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Estado;
import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

public class EstadoDAOImpl extends BaseDAOImpl<Estado> implements EstadoDAO {

	@Inject
	private MunicipioDAO municipioDAO;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Estado> findAll() {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CODIGO_IBGE, 			\n");
		sql.append("	   INITCAP(NOME) AS NOME, 	\n");
		sql.append("	   UF 	 		 			\n");
		sql.append("	FROM  ").append(Constantes.SCHEMA_OIBDMUB).append(".DG_UF \n");
		sql.append("	ORDER BY NOME 				\n");
		
		Query query = em.createNativeQuery(sql.toString(), Estado.class);
		List<Estado> lista = (List<Estado>) query.getResultList();		
		
		return lista;
	}	
	
	@Override
	@Transactional
	public Estado findByIdWithMunicipios(Long id) {
		Estado estado = findById(id);
		List<Municipio> municipios = municipioDAO.findByUF(estado.getUf());
		estado.setMunicipios(municipios);
		return estado;
	}
	
}