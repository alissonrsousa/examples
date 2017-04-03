package br.com.digicade.geosite.geoestatistica.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_MUNICIPIO", schema=Constantes.SCHEMA_OIBDMUB)
public class Municipio extends BaseEntityImpl<Municipio> {
	
	private static final long serialVersionUID = -7063887004979043578L;

	@Id
	@Column(name="FID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="UF")
	private String uf;
	
	@OneToMany(mappedBy="municipio", fetch=FetchType.LAZY)
	private List<Localidade> localidades;

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
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public List<Localidade> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(List<Localidade> localidades) {
		this.localidades = localidades;
	}

}
