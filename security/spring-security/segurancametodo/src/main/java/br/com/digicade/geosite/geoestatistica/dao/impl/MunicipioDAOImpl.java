package br.com.digicade.geosite.geoestatistica.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import com.wideplay.warp.persist.Transactional;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;
import br.com.digicade.geosite.geoestatistica.dao.MunicipioDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Municipio;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

public class MunicipioDAOImpl extends BaseDAOImpl<Municipio> implements MunicipioDAO {

	@Override
	@Transactional
	public Municipio findByIdWithLocalidades(Long id) {
		Municipio municipio = findById(id);
		Hibernate.initialize(municipio.getLocalidades());
		return municipio;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Municipio> findByUF(String uf) {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT FID, 					\n");
		sql.append("	   INITCAP(NOME) AS NOME, 	\n");
		sql.append("	   UF 	 		 			\n");
		sql.append("	FROM  ").append(Constantes.SCHEMA_OIBDMUB).append(".DG_MUNICIPIO \n");
		sql.append("	WHERE UF = ?	 			\n");
		sql.append("	ORDER BY NOME 				\n");
		
		Query query = em.createNativeQuery(sql.toString(), Municipio.class);
		query.setParameter(1, uf);
		List<Municipio> lista = (List<Municipio>) query.getResultList();		
		
		return lista;
	}

}