package br.com.digicade.geosite.geoestatistica.model.vo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.digicade.geosite.geoestatistica.anotacoes.cabecalhoColuna;

@Entity
public class LinhaRelatorioDetalhadoVO {
	
	@Id
	private Long id;
	
	@cabecalhoColuna(nome="Bairro")
	private String BAIRRO;
	@cabecalhoColuna(nome="Caixa Terminal")
	private String CAIXA_TERMINAL;	
	@cabecalhoColuna(nome="Código Estação")
	private String COD_ESTACAO;
	@cabecalhoColuna(nome="Código Logradouro")
	private String COD_LOGRADOURO;
	@cabecalhoColuna(nome="Código")
	private String CODIGO;
	@cabecalhoColuna(nome="Complemento")
	private String COMPLEMENTO;
	@cabecalhoColuna(nome="Id Localidade")
	private String ID_LOCALIDADE;
	@cabecalhoColuna(nome="Localidade")
	private String LOCALIDADE;
	@cabecalhoColuna(nome="Logradouro")
	private String LOGRADOURO;
	@cabecalhoColuna(nome="Nome")
	private String NOME;
	@cabecalhoColuna(nome="Numeração Predial")
	private String NRO_PREDIAL;
	@cabecalhoColuna(nome="Número Terminal")
	private String NRO_TERMINAL;
	@cabecalhoColuna(nome="Seção Serviço")
	private String SEC_SERVICO;
	@cabecalhoColuna(nome="Tipo Caixa Terminal")
	private String TIPO_CAIXA_TERMINAL;
	@cabecalhoColuna(nome="Tipo Estação")
	private String TIPO_ESTACAO;
	@cabecalhoColuna(nome="Tipo Seção Serviço")
	private String TIPO_SEC_SERVICO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBAIRRO() {
		return BAIRRO;
	}
	public void setBAIRRO(String bAIRRO) {
		BAIRRO = bAIRRO;
	}
	public String getCAIXA_TERMINAL() {
		return CAIXA_TERMINAL;
	}
	public void setCAIXA_TERMINAL(String cAIXA_TERMINAL) {
		CAIXA_TERMINAL = cAIXA_TERMINAL;
	}
	public String getCOD_ESTACAO() {
		return COD_ESTACAO;
	}
	public void setCOD_ESTACAO(String cOD_ESTACAO) {
		COD_ESTACAO = cOD_ESTACAO;
	}
	public String getCOD_LOGRADOURO() {
		return COD_LOGRADOURO;
	}
	public void setCOD_LOGRADOURO(String cOD_LOGRADOURO) {
		COD_LOGRADOURO = cOD_LOGRADOURO;
	}
	public String getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}
	public String getCOMPLEMENTO() {
		return COMPLEMENTO;
	}
	public void setCOMPLEMENTO(String cOMPLEMENTO) {
		COMPLEMENTO = cOMPLEMENTO;
	}
	public String getID_LOCALIDADE() {
		return ID_LOCALIDADE;
	}
	public void setID_LOCALIDADE(String iD_LOCALIDADE) {
		ID_LOCALIDADE = iD_LOCALIDADE;
	}
	public String getLOCALIDADE() {
		return LOCALIDADE;
	}
	public void setLOCALIDADE(String lOCALIDADE) {
		LOCALIDADE = lOCALIDADE;
	}
	public String getLOGRADOURO() {
		return LOGRADOURO;
	}
	public void setLOGRADOURO(String lOGRADOURO) {
		LOGRADOURO = lOGRADOURO;
	}
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getNRO_PREDIAL() {
		return NRO_PREDIAL;
	}
	public void setNRO_PREDIAL(String nRO_PREDIAL) {
		NRO_PREDIAL = nRO_PREDIAL;
	}
	public String getNRO_TERMINAL() {
		return NRO_TERMINAL;
	}
	public void setNRO_TERMINAL(String nRO_TERMINAL) {
		NRO_TERMINAL = nRO_TERMINAL;
	}
	public String getSEC_SERVICO() {
		return SEC_SERVICO;
	}
	public void setSEC_SERVICO(String sEC_SERVICO) {
		SEC_SERVICO = sEC_SERVICO;
	}
	public String getTIPO_CAIXA_TERMINAL() {
		return TIPO_CAIXA_TERMINAL;
	}
	public void setTIPO_CAIXA_TERMINAL(String tIPO_CAIXA_TERMINAL) {
		TIPO_CAIXA_TERMINAL = tIPO_CAIXA_TERMINAL;
	}
	public String getTIPO_ESTACAO() {
		return TIPO_ESTACAO;
	}
	public void setTIPO_ESTACAO(String tIPO_ESTACAO) {
		TIPO_ESTACAO = tIPO_ESTACAO;
	}
	public String getTIPO_SEC_SERVICO() {
		return TIPO_SEC_SERVICO;
	}
	public void setTIPO_SEC_SERVICO(String tIPO_SEC_SERVICO) {
		TIPO_SEC_SERVICO = tIPO_SEC_SERVICO;
	}
	
	public static String getCabecalhoColuna(String nomeColuna){
		
		Class classe = LinhaRelatorioDetalhadoVO.class;
	  	for(Field field : classe.getDeclaredFields()){
	  		if(nomeColuna.equals(field.getName())){
	  			Annotation[] annotations = field.getDeclaredAnnotations();
	  			for(Annotation annotation : annotations){
				    if(annotation instanceof cabecalhoColuna){
				    	cabecalhoColuna myAnnotation = (cabecalhoColuna) annotation;
				        return myAnnotation.nome();
				    }
				}
	  		}
	  	}
	  	return null;
	}	
	
//	public static String getColumnName(String nomeColuna){
//		
//		Class classe = LinhaRelatorioDetalhadoVO.class;
//	  	for(Field field : classe.getDeclaredFields()){
//	  		//if(nomeAtributo == field.getName()){
//	  			Annotation[] annotations = field.getDeclaredAnnotations();
//	  			for(Annotation annotation : annotations){
//				    if(annotation instanceof Column){
//				        Column myAnnotation = (Column) annotation;
//				        if(myAnnotation.name().equals(nomeColuna)){
//					        System.out.println("name: " + myAnnotation.name());
//					        return field.getName();
//				        }
//				    }
//				}
//	  		//}
//	  	}
//	  	return null;
//	}
	
}
