package br.com.digicade.geosite.geoestatistica.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.WordUtils;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_LOCALIDADE", schema=Constantes.SCHEMA_OIBDMUB)
public class Localidade extends BaseEntityImpl<Localidade> {
	
	private static final long serialVersionUID = -7063887004979043578L;

	@Id
	@Column(name="FID")
	private Long id;
	
	@Column(name="NOME_OFICIAL")
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FID_MUNICIPIO", nullable=false, insertable=false, updatable=false)
	private Municipio municipio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return WordUtils.capitalizeFully(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
