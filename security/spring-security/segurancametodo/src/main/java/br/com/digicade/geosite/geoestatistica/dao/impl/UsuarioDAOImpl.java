package br.com.digicade.geosite.geoestatistica.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.wideplay.warp.persist.Transactional;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;
import br.com.digicade.geosite.geoestatistica.dao.UsuarioDAO;
import br.com.digicade.geosite.geoestatistica.model.entity.Usuario;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> findAll() {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT							\n");
		sql.append("	   ID_USU, 							\n");
		sql.append("	   INITCAP(NOME_USU) AS NOME_USU, 	\n");
		sql.append("	   STATUS_USU 			 	 		\n");
		sql.append("	FROM  ").append(Constantes.SCHEMA_OIBDMUB).append(".VM_USUARIO \n");
		sql.append("	ORDER BY NOME_USU 					\n");
		
		Query query = em.createNativeQuery(sql.toString(), Usuario.class);
		List<Usuario> lista = (List<Usuario>) query.getResultList();		
		
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> findByStatus(String status) throws Exception {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_USU, 							\n");
		sql.append("	   INITCAP(NOME_USU) AS NOME_USU 	\n");
		sql.append("	   ,STATUS_USU AS STATUS_USU 	 	\n");
		sql.append("	FROM  ").append(Constantes.SCHEMA_OIBDMUB).append(".VM_USUARIO \n");
		sql.append("	WHERE STATUS_USU LIKE ?	 			\n");
		sql.append("	ORDER BY NOME_USU 					\n");
		
		Query query = em.createNativeQuery(sql.toString(), Usuario.class);
		query.setParameter(1, "%"+status+"%");
		List<Usuario> lista = (List<Usuario>) query.getResultList();		
		
		return lista;
	}

}