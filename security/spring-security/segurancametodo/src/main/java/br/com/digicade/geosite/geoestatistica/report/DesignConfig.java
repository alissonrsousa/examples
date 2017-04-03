package br.com.digicade.geosite.geoestatistica.report;

import java.util.List;

public class DesignConfig {
	private List<ColumnConfig> colunas;
	private boolean showLogo;
	private String titulo;
	private String subTitulo;
	
	public List<ColumnConfig> getColunas() {
		return colunas;
	}
	public void setColunas(List<ColumnConfig> colunas) {
		this.colunas = colunas;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
	public boolean isShowLogo() {
		return showLogo;
	}
	public void setShowLogo(boolean showLogo) {
		this.showLogo = showLogo;
	}	
}