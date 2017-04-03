package br.com.digicade.geosite.geoestatistica.report;

public class ColumnConfig {
	int width;
	String text;
	String dataIndex;
	String valueClassName;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDataIndex() {
		return dataIndex;
	}
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}
	public String getValueClassName() {
		return valueClassName;
	}
	public void setValueClassName(String valueClassName) {
		this.valueClassName = valueClassName;
	}
}