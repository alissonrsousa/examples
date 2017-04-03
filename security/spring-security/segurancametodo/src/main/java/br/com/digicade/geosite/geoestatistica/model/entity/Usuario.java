package br.com.digicade.geosite.geoestatistica.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="VM_USUARIO", schema=Constantes.SCHEMA_OIBDMUB)
public class Usuario extends BaseEntityImpl<Usuario> {
	
	private static final long serialVersionUID = 4095303137319286077L;

	@Id
	@Column(name="ID_USU")
	private Long id;
	
	@Column(name="NOME_USU")
	private String nome;
	
	@Column(name="STATUS_USU")
	private String status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

}
