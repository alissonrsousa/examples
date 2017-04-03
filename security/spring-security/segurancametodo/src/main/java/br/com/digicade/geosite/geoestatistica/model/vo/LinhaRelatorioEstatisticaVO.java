package br.com.digicade.geosite.geoestatistica.model.vo;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class LinhaRelatorioEstatisticaVO {

	@Id
	@Column(name="ENTITYID_ATRIBUTO")
	private Long id;
	@Column(name="NOME_ATRIBUTO")
	private String atributo;	
	@Column(name="QTDE_TOTAL")
	private Long quantidade;
	@Column(name="QTDE_ESPACIALIZADO")
	private Long quantidadeEspacializado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Long getQuantidadeEspacializado() {
		return quantidadeEspacializado;
	}
	public void setQuantidadeEspacializado(Long quantidadeEspacializado) {
		this.quantidadeEspacializado = quantidadeEspacializado;
	}
	@Transient
	public Long getQuantidadeNaoEspacializado() {
		return quantidade - quantidadeEspacializado;
	}	
	@Transient
	public String getPorcentagemEspacializado() {
		Double retorno = (Double.valueOf(quantidadeEspacializado)/Double.valueOf(quantidade))*100;
		return String.valueOf(converterDoubleDoisDecimais(retorno)) + " %";
	}	
	
	public double converterDoubleDoisDecimais(double precoDouble) {  
	    DecimalFormat fmt = new DecimalFormat("0.00");        
	    String string = fmt.format(precoDouble);  
	    
	    double preco;
	    if(string.indexOf(",") != -1){
		    String[] part = string.split("[,]");  
		    String string2 = part[0]+"."+part[1]; 
		    preco = Double.parseDouble(string2); 
	    }
	    else{
	    	preco = Double.parseDouble(string); 
	    }
	    return preco;  
	} 	
	
}
