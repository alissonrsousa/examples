package br.com.digicade.geosite.geoestatistica.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.digicade.commons.base.dao.impl.BaseDAOImpl;
import br.com.digicade.geosite.geoestatistica.dao.EstatisticaDAO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.FiltroEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioDetalhadoVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioEstatisticaVO;
import br.com.digicade.geosite.geoestatistica.model.vo.LinhaRelatorioGerenciaEspacializacaoVO;

import com.wideplay.warp.persist.Transactional;

public class EstatisticaDAOImpl extends BaseDAOImpl implements EstatisticaDAO {

	@Override
	@Transactional
	public List<LinhaRelatorioEstatisticaVO> getEstatisticas(FiltroEstatisticaVO filtro) {
		
		EntityManager em = this.getEntityManager();
		
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder sql = montaQueryEstatisticas(filtro, parameters);
		
		Query query = em.createNativeQuery(sql.toString(), LinhaRelatorioEstatisticaVO.class);
		
		for(int i = 0; i < parameters.size(); i++){
			query.setParameter(i+1, parameters.get(i));
		}
		List<LinhaRelatorioEstatisticaVO> lista = (List<LinhaRelatorioEstatisticaVO>) query.getResultList();	
		
		return lista;
	}
	
	public StringBuilder montaQueryEstatisticas(FiltroEstatisticaVO filtro, List<Object> parameters) {
		
		parameters.add(filtro.getListaAtributos());
		parameters.add(filtro.getEstado());		
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM TABLE( 											\n");
		sql.append("GEOSITE.PKG_GEOSITE_ESTATISTICA.FNC_RELATORIO_ESTATISTICA(?, ?	\n");
		if(filtro.getIdMunicipio() != null){
			sql.append(", ?															\n");
			parameters.add(filtro.getIdMunicipio());
		}
		else{
			sql.append(", NULL														\n");
		}
		if(filtro.getIdLocalidade() != null){
			sql.append(", ?															\n");
			parameters.add(filtro.getIdLocalidade());
		}
		else{
			sql.append(", NULL														\n");
		}
		if(filtro.getIdUsuario() != null){
			sql.append(", ?															\n");
			parameters.add(filtro.getIdUsuario());
		}
		else{
			sql.append(", NULL														\n");
		}	
		if(filtro.getDataInicio() != null){
			sql.append(", ?															\n");
			parameters.add(filtro.getDataInicio());
		}
		else{
			sql.append(", NULL														\n");
		}	
		if(filtro.getDataFim() != null){
			sql.append(", ?															\n");
			parameters.add(filtro.getDataFim());
		}
		else{
			sql.append(", NULL														\n");
		}		
		sql.append("))																\n");
		return sql;
	}
	
	@Override
	@Transactional
	public List<LinhaRelatorioGerenciaEspacializacaoVO> getEstatisticasGerenciaEspacializacao(FiltroEstatisticaVO filtro) {
		EntityManager em = this.getEntityManager();
		
		List<Object> parameters = new ArrayList<Object>();
		StringBuilder sql = montaQueryGerenciaEspacializacao(filtro, parameters);
		
		Query query = em.createNativeQuery(sql.toString(), LinhaRelatorioGerenciaEspacializacaoVO.class);
		
		for(int i = 0; i < parameters.size(); i++){
			query.setParameter(i+1, parameters.get(i));
		}
		List<LinhaRelatorioGerenciaEspacializacaoVO> lista = (List<LinhaRelatorioGerenciaEspacializacaoVO>) query.getResultList();	
		
		return lista;
	}	
	
	public StringBuilder montaQueryGerenciaEspacializacao(FiltroEstatisticaVO filtro, List<Object> parameters) {
		
		parameters.add(filtro.getListaAtributos());
		parameters.add(filtro.getEstado());		
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM TABLE( 												\n");
		sql.append("GEOSITE.PKG_GEOSITE_ESTATISTICA.FNC_GERENCIA_ESPACIALIZACAO(?, ?	\n");
		if(filtro.getIdUsuario() != null){
			sql.append(", ?																\n");
			parameters.add(filtro.getIdUsuario());
		}
		else{
			sql.append(", NULL															\n");
		}	
		if(filtro.getDataInicio() != null){
			sql.append(", ?																\n");
			parameters.add(filtro.getDataInicio());
		}
		else{
			sql.append(", NULL															\n");
		}	
		if(filtro.getDataFim() != null){
			sql.append(", ?																\n");
			parameters.add(filtro.getDataFim());
		}
		else{
			sql.append(", NULL															\n");
		}		
		sql.append("))																	\n");
		return sql;
	}	
	
	@Override
	public List<String> getColunasEstatisticasDetalhado(Long idAtributo) {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT COLUNAS_DETAHE FROM GEOSITE.DG_ESTATISTICA_ATRIBUTO WHERE ENTITYID = ?");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter(1, idAtributo);
		String colunasString = query.getSingleResult().toString();
		
		String[] arrayColunas = colunasString.split(",");
		
		List<String> colunas = new ArrayList<String>();
		
		for(String coluna: arrayColunas){
			colunas.add(coluna.trim());
		}
		
		return colunas;
	}		

	@Override
	public List<LinhaRelatorioDetalhadoVO> getEstatisticasDetalhado(FiltroEstatisticaDetalhadoVO filtro) {
		EntityManager em = this.getEntityManager();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 'SELECT ROWNUM AS ID, A.* ' || CHR(10) ||																								");
		sql.append("   '  FROM TABLE(GEOSITE.PKG_GEOSITE_ESTATISTICA.FNC_REL_ESTATISTICA_DETALHE(").append(filtro.getIndEspacializado()).append(", ' || CHR(10) ||	");
		sql.append("   '                                                                         ").append(filtro.getIdAtributo()).append(", ' || CHR(10) ||		");
		sql.append("   '                                                                         ''").append(filtro.getEstado()).append("'',  ' || CHR(10) ||		");
		if(filtro.getIdMunicipio() != null){
			sql.append("   '                                                                     ").append(filtro.getIdMunicipio()).append(",  ' || CHR(10) ||		");
		}
		else{
			sql.append("   '                                                                         NULL,  ' || CHR(10) ||											");
		}
		if(filtro.getIdLocalidade() != null){
			sql.append("   '                                                                     ").append(filtro.getIdLocalidade()).append(",  ' || CHR(10) ||		");
		}
		else{
			sql.append("   '                                                                         NULL,  ' || CHR(10) ||											");
		}
		if(filtro.getIdUsuario() != null){
			sql.append("   '                                                                     ").append(filtro.getIdUsuario()).append(",  ' || CHR(10) ||		");
		}
		else{
			sql.append("   '                                                                         NULL,  ' || CHR(10) ||											");
		}	
		if(filtro.getDataInicio() != null){
			sql.append("   '                                                                     ").append(filtro.getDataInicio()).append(",  ' || CHR(10) ||		");
		}
		else{
			sql.append("   '                                                                         NULL,  ' || CHR(10) ||											");
		}	
		if(filtro.getDataFim() != null){
			sql.append("   '                                                                     ").append(filtro.getDataFim()).append("))A'						");
		}
		else{
			sql.append("   '                                                                         NULL))A'														");
		}	
		sql.append("  FROM GEOSITE.DG_ESTATISTICA_ATRIBUTO																											");	
		sql.append("  WHERE ENTITYID = ?																															");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter(1, filtro.getIdAtributo());
		
		String sqlConsulta = query.getSingleResult().toString();
		
		query = em.createNativeQuery(sqlConsulta, LinhaRelatorioDetalhadoVO.class);
		
		List<LinhaRelatorioDetalhadoVO> lista = (List<LinhaRelatorioDetalhadoVO>) query.getResultList();	
		
		return lista;
	}

}
