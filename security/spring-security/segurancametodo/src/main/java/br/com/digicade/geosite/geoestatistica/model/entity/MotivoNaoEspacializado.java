package br.com.digicade.geosite.geoestatistica.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_MOTIVO_NAO_ESPACIALIZADO", schema=Constantes.SCHEMA_OIBDMUB)
public class MotivoNaoEspacializado extends BaseEntityImpl<MotivoNaoEspacializado> {
	
	private static final long serialVersionUID = 6886335139380220968L;

	@Id
	@Column(name="FID")
	private Long id;
	
	@Column(name="DESCRICAO")
	private String descricao;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
