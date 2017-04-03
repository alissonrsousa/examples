package br.com.digicade.geosite.geoestatistica.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;

public class JPAUtil extends BaseDAOImpl{
	private List<String> getColumnNames(final String sql) {
		EntityManager em = this.getEntityManager();
		
		Session session = em.unwrap(Session.class);
		final List<String> colunas = new ArrayList<String>();
		
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException{
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSetMetaData rsmd = stmt.getMetaData();
				
				for(int i = 0; i < rsmd.getColumnCount(); i++){
					colunas.add(rsmd.getColumnLabel(i+1));
				}
			}
		});
		
		return colunas;
	}
	
	public JSONArray getJsonResultList(String sql, List<Object> parameters, Integer start, Integer limit){
		EntityManager em = this.getEntityManager();
		
		JSONArray result = new JSONArray();
		
		List<String> colunas = this.getColumnNames(sql);
		Query query = em.createNativeQuery(sql);
		
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i+1, parameters.get(i));
			}
		}
		
		if(start != null && limit != null){
			query.setFirstResult(start);
			query.setMaxResults(limit);	
		}
		
		@SuppressWarnings("unchecked")
		List<Object> rows = query.getResultList();
		
		for(int i = 0; i < rows.size(); i++){
			JSONObject row = new JSONObject();
			for(int j = 0; j < colunas.size(); j++){
				String coluna = colunas.get(j);
				Object valor = ((Object[]) rows.get(i))[j];
				
				row.put(coluna, valor);
			}
			result.add(row);
		}
		
		return result;
	}
	
	public JSONArray getJsonResultList(String sql, Integer start, Integer limit){
		return getJsonResultList(sql, null, start, limit);
	}
	
	public JSONArray getJsonResultList(String sql){
		return getJsonResultList(sql, null, null, null);
	}
	
	public JSONArray getJsonResultList(String sql, List<Object> parameters){
		return getJsonResultList(sql, parameters, null, null);
	}
}