package br.com.digicade.geosite.geoestatistica.report;

import java.awt.Color;
import java.util.List;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

public class DynamicReportDesign extends JasperDesign {
	private static final long serialVersionUID = 1L;
	
	private DesignConfig config;
	private JRDesignImage logo;
	private JRDesignStaticText reportTitle;
	private JRDesignStaticText reportSubTitle;
	
	public DynamicReportDesign(DesignConfig config) throws JRException{
		this.config = config;
		
		this.createReportBands();
		this.addFields(this.config.getColunas());
		this.designConfig();
		this.titleConfig();
	}
	
	@SuppressWarnings("deprecation")
	private void createReportBands(){
		//title band
		JRDesignBand titleBand = new JRDesignBand();
		titleBand.setHeight(50);
		titleBand.setSplitType(SplitTypeEnum.STRETCH);
		this.setTitle(titleBand);
		
		//column header band
		JRDesignBand columnHeaderBand = new JRDesignBand();
		columnHeaderBand.setHeight(20);
		JRDesignExpression exp = new JRDesignExpression();
        exp.setText("($V{PAGE_NUMBER})==1");
        exp.setValueClass(Boolean.class);
        columnHeaderBand.setPrintWhenExpression(exp);
        this.setColumnHeader(columnHeaderBand);
        
        //detail Band
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(13);
        
        this.setDetail(detailBand);
	}
	
	@SuppressWarnings("deprecation")
	private void addField(ColumnConfig column) throws JRException{
		int nextColumnPositionX = this.getNextColumnPositionX();
		
		//adiciona campo
		JRDesignField field = new JRDesignField();
		field.setName(column.getDataIndex());		
		field.setValueClassName(column.getValueClassName());
		this.addField(field);
		
		//adiciona cabecalho
		JRDesignStaticText columnHeader = new JRDesignStaticText();
		columnHeader.setWidth(column.getWidth());
		columnHeader.setHeight(20);
		columnHeader.setFontSize(10);
		columnHeader.setX(nextColumnPositionX);
		columnHeader.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
		columnHeader.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		columnHeader.setText(column.getText());
		columnHeader.setBold(true);
		columnHeader.setBackcolor(new Color(204,204,204));
		columnHeader.setMode(ModeEnum.OPAQUE);
		columnHeader.getLineBox().getPen().setLineWidth((float) 0.9);
		columnHeader.getLineBox().getPen().setLineStyle(LineStyleEnum.SOLID);
		columnHeader.getLineBox().getPen().setLineColor(Color.BLACK);
		columnHeader.setStretchType(StretchTypeEnum.RELATIVE_TO_BAND_HEIGHT);
		((JRDesignBand)this.getColumnHeader()).addElement(columnHeader);
		
		
		//adiciona referencia do campo
		JRDesignTextField textField = new JRDesignTextField();
		textField.setBlankWhenNull(true);
		textField.setWidth(column.getWidth());
		textField.setHeight(13);
		textField.setFontSize(8);
		textField.setMode(ModeEnum.TRANSPARENT);
		textField.getLineBox().getPen().setLineWidth((float) 0.5);
		textField.getLineBox().getPen().setLineStyle(LineStyleEnum.SOLID);
		textField.getLineBox().getPen().setLineColor(Color.BLACK);
		textField.setX(nextColumnPositionX);
		
		textField.setStretchWithOverflow(true);
		textField.setPrintWhenDetailOverflows(true);		
		textField.setStretchType(StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT);
		
		textField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
		JRDesignExpression expression = new JRDesignExpression();
		expression.setValueClassName(column.getValueClassName());
		expression.setText("$F{"+column.getDataIndex()+"}");
		textField.setExpression(expression);
		
		((JRDesignBand)this.getDetail()).addElement(textField);
	}
	
	private void addFields(List<ColumnConfig> columns) throws JRException{
		for(int i = 0; i < columns.size(); i++){
			this.addField(columns.get(i));
		}
	}
	
	private void designConfig(){
		this.setName("DynamicReportDesign");
		this.setOrientation(OrientationEnum.PORTRAIT);
		this.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		this.setLeftMargin(0);
		this.setRightMargin(0);
		this.setBottomMargin(0);
		this.setTopMargin(0);
	}
	
	private void titleConfig() throws JRException{
		JRDesignBand titleBand = (JRDesignBand) this.getTitle();
		
		//adiciona logo
		if(this.config.isShowLogo()){
			JRDesignParameter imgLogoParam = new JRDesignParameter();
			imgLogoParam.setName("IMG_LOGO_PATH");
			imgLogoParam.setValueClass(String.class);
			this.addParameter(imgLogoParam);
			
			this.logo = new JRDesignImage(this);
			this.logo.setX(0);
			this.logo.setY(0);
			this.logo.setHeight(45);
			this.logo.setWidth(49);
			JRDesignExpression logoExpression = new JRDesignExpression();
			logoExpression.setValueClass(String.class);
			logoExpression.setText("$P{IMG_LOGO_PATH}");
			this.logo.setExpression(logoExpression);		
			titleBand.addElement(this.logo);			
		}

		
		//adiciona titulo
		if(this.config.getTitulo() != null){
			this.reportTitle = new JRDesignStaticText();
			this.reportTitle.setX(this.logo.getWidth()+10);
			this.reportTitle.setY(0);
			this.reportTitle.setHeight(titleBand.getHeight() / (this.config.getSubTitulo() != null ? 2 : 1));
			this.reportTitle.setForecolor(new Color(184,71,0));
			this.reportTitle.setBold(true);
			this.reportTitle.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
			this.reportTitle.setFontSize(14);
			this.reportTitle.setText(this.config.getTitulo());
			this.reportTitle.setWidth(this.getNextColumnPositionX()-this.logo.getWidth());
			titleBand.addElement(this.reportTitle);
		}
		
		//adiciona subTitulo
		if(this.config.getSubTitulo() != null){
			this.reportSubTitle = new JRDesignStaticText();
			this.reportSubTitle.setX(logo.getWidth()+10);
			this.reportSubTitle.setY(this.reportTitle != null ? this.reportTitle.getHeight() : 0);
			this.reportSubTitle.setHeight(titleBand.getHeight() / (this.config.getTitulo() != null ? 2 : 1));
			this.reportSubTitle.setForecolor(new Color(184,71,0));
			this.reportSubTitle.setBold(true);
			this.reportSubTitle.setItalic(true);
			this.reportSubTitle.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
			this.reportSubTitle.setFontSize(12);
			this.reportSubTitle.setText(this.config.getSubTitulo());
			this.reportSubTitle.setWidth(this.getNextColumnPositionX()-logo.getWidth());
			titleBand.addElement(this.reportSubTitle);
		}
	}
	
	private int getNextColumnPositionX(){
		JRElement[] colunas = this.getColumnHeader().getElements();
		int width = 0;
		for(int i = 0; i < colunas.length; i++){
			width += colunas[i].getWidth();
		}
		return width;
	}
}