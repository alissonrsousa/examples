package br.com.digicade.geosite.geoestatistica.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_UF", schema=Constantes.SCHEMA_OIBDMUB)
public class Estado extends BaseEntityImpl<Estado> {
	
	private static final long serialVersionUID = 4095303137319286077L;

	@Id
	@Column(name="CODIGO_IBGE")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="UF")
	private String uf;	
	
	@Transient
	private List<Municipio> municipios;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

}
