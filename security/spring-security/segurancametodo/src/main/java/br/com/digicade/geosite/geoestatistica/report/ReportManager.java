package br.com.digicade.geosite.geoestatistica.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;

public class ReportManager {
	private JasperDesign design;
	@SuppressWarnings("rawtypes")
	private List dataSet;
	private Map<String, Object> parametros;
	
	@SuppressWarnings("rawtypes")
	public ReportManager(JasperDesign design, List dataSet, Map<String, Object> parametros){
		this.design = design;
		this.dataSet = dataSet;
		this.parametros = parametros;
	}
	
	public byte[] getExcelFile() throws JRException, IOException{
		
		byte[] data = null;
		JExcelApiExporter exporter = new JExcelApiExporter();
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		
		if(this.dataSet.size() < 65001){
			
			if(this.parametros == null){
				this.parametros = new HashMap<String, Object>();
			}
			
			JasperReport relatorio = JasperCompileManager.compileReport(this.design);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.dataSet);
			JasperPrint impressao = JasperFillManager.fillReport(relatorio, this.parametros, dataSource);	
			exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, impressao);
		}
		else{
			
			int qdeSheets = this.dataSet.size() / 65000 +(this.dataSet.size() % 65000 == 0?0:1);
			List<JasperPrint> listaSheets = new ArrayList<JasperPrint>();
			
			for(int i=0; i<qdeSheets; i++){
				List dataSetSheet = this.dataSet.subList(i*65000, (i*65000+65000<=this.dataSet.size()?(i*65000+65000):(this.dataSet.size())));
				JRBeanCollectionDataSource dataSourceSheet = new JRBeanCollectionDataSource(dataSetSheet);
				JasperReport sheet = JasperCompileManager.compileReport(this.design);
				JasperPrint divisao = JasperFillManager.fillReport(sheet, this.parametros, dataSourceSheet);	
				
				listaSheets.add(divisao);
			}
			
			exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT_LIST, listaSheets);
		}

		exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, xlsReport);
		exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		
//		exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.TRUE);			
//      exporter.setParameter(JExcelApiExporterParameter.OUTPUT_FILE_NAME,"F://Samplejrxml//demo1.xls");
//      exporter.setParameter(JExcelApiExporterParameter.SHEET_NAMES, new String[]{"Personal Information", "Skills"});		
		exporter.exportReport();   
		data = xlsReport.toByteArray();   
		xlsReport.close();
		
		return data;
	}
	
	public byte[] getPdfFile() throws JRException, IOException{
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(this.dataSet);
		JasperReport relatorio = JasperCompileManager.compileReport(this.design);
		
		byte[] data = null;
		
		if(this.parametros == null){
			this.parametros = new HashMap<String, Object>();
		}
		ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
		
		JasperPrint impressao = JasperFillManager.fillReport(relatorio, this.parametros,dataSource);
		JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "./teste.pdf");
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
		exporter.exportReport();	
		data = pdfReport.toByteArray();   
		
		return data;
	}
}
