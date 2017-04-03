package br.com.digicade.geosite.geoestatistica.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.digicade.commons.base.entity.BaseEntityImpl;
import br.com.digicade.geosite.geoestatistica.util.Constantes;

@Entity
@Table(name="DG_ESTATISTICA_TIPO", schema=Constantes.SCHEMA_GEOSITE)
public class TipoConsulta extends BaseEntityImpl<TipoConsulta> {

	private static final long serialVersionUID = -3113321622355570438L;
	
	@Id
	//Ver Sequence Correta
	@SequenceGenerator(name="sequenceTipoConsulta", schema=Constantes.SCHEMA_GEOSITE, sequenceName=Constantes.SCHEMA_GEOSITE+"SEQ_LOCALIDADE_CPL")
	@GeneratedValue(generator="sequenceTipoConsulta", strategy=GenerationType.SEQUENCE)
	
	@Column(name="ENTITYID")
	private Long id;
	@Column(name="DESCRICAO")
	private String descricao;
	
	@OneToMany(mappedBy="tipoConsulta", fetch=FetchType.LAZY)
	private List<AtributoEstatistica> atributos;
	
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
	public List<AtributoEstatistica> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<AtributoEstatistica> atributos) {
		this.atributos = atributos;
	}
	
}
