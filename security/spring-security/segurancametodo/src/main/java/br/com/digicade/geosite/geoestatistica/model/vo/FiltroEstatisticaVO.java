package br.com.digicade.geosite.geoestatistica.model.vo;

import java.util.Date;

public class FiltroEstatisticaVO {

	private Integer idTipoConsulta;
	private String listaAtributos;
	private String estado;
	private Long idMunicipio;
	private Long idLocalidade;
	private Long idUsuario;
	private Date dataInicio;
	private Date dataFim;
	private String tipoExportacao;
	private String localSelecionado;
	private String descricaoFiltro;
	
	public Integer getIdTipoConsulta() {
		return idTipoConsulta;
	}
	public void setIdTipoConsulta(Integer idTipoConsulta) {
		this.idTipoConsulta = idTipoConsulta;
	}
	public String getListaAtributos() {
		return listaAtributos;
	}
	public void setListaAtributos(String listaAtributos) {
		this.listaAtributos = listaAtributos;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public Long getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Long idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getTipoExportacao() {
		return tipoExportacao;
	}
	public void setTipoExportacao(String tipoExportacao) {
		this.tipoExportacao = tipoExportacao;
	}
	public String getLocalSelecionado() {
		return localSelecionado;
	}
	public void setLocalSelecionado(String localSelecionado) {
		this.localSelecionado = localSelecionado;
	}
	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}
	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}
	
}
