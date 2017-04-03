package br.com.digicade.geosite.geoestatistica.model.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoConsultaVO {
	@Id
	private long id;
	private String descricao;
	
	public long getId() {
		return id;
	}
	public void setId(long idTipoLogradouro) {
		this.id = idTipoLogradouro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
