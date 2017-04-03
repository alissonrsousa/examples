package br.com.digicade.geosite.geoestatistica.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_ESTATISTICA_ATRIBUTO", schema=Constantes.SCHEMA_GEOSITE)
public class AtributoEstatistica {
	
	@Id
	@SequenceGenerator(name="sequenceAtributoEstatistica", schema=Constantes.SCHEMA_GEOSITE, sequenceName=Constantes.SCHEMA_GEOSITE+"SEQ_LOCALIDADE_CPL")
	@GeneratedValue(generator="sequenceAtributoEstatistica", strategy=GenerationType.SEQUENCE)
	
	@Column(name="ENTITYID")
	private long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_CONSULTA", nullable=false, insertable=false, updatable=false)
	private TipoConsulta tipoConsulta;
	
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
	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	} 

}
