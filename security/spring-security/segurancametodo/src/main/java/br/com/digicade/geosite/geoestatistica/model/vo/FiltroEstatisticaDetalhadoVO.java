package br.com.digicade.geosite.geoestatistica.model.vo;

import java.util.Date;

public class FiltroEstatisticaDetalhadoVO {

	private Integer indEspacializado;
	private Long idAtributo;
	private String estado;
	private Long idMunicipio;
	private Long idLocalidade;
	private Long idUsuario;
	private Date dataInicio;
	private Date dataFim;
	private String tipoExportacao;
	private String descricaoFiltro;
	
	public Integer getIndEspacializado() {
		return indEspacializado;
	}
	public void setIndEspacializado(Integer indEspacializado) {
		this.indEspacializado = indEspacializado;
	}
	public Long getIdAtributo() {
		return idAtributo;
	}
	public void setIdAtributo(Long idAtributo) {
		this.idAtributo = idAtributo;
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
	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}
	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}
	
}
