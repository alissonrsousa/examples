package br.com.digicade.geosite.geoestatistica.model.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AtributoEstatisticaVO {
	@Id
	private long id;
	private String descricao;
	private long idTipoConsulta;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getIdTipoConsulta() {
		return idTipoConsulta;
	}
	public void setIdTipoConsulta(long idTipoConsulta) {
		this.idTipoConsulta = idTipoConsulta;
	}
	
}
