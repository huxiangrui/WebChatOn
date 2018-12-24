package reportwriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import path.Path;
import report.SummaryReport;
import reportservice.SummaryService;

public class SummaryReportWriter {
	private static final String BASE_PATH=Path.filepath+"/WebRoot/report";
	private static final String DIRECTORY_PATH=BASE_PATH+"/summary";
	
	//成员变量
	private Document document;
	private SummaryReport summaryReport;
	private ArrayList<SummaryService> summaryServices;
	private String filePath;
	private File file;
	
	public SummaryReportWriter(SummaryReport summaryReport){
		this.setFilePath(DIRECTORY_PATH+"/"+"summary-"+summaryReport.getReportDateString()+".xml");
		createFolder(BASE_PATH);
		createFolder(DIRECTORY_PATH);
		createFile(getFilePath());
		this.setFile(new File(getFilePath()));
		
		
		this.summaryReport=summaryReport;
		this.summaryServices=getSummaryReport().getSummaryServiceList();		
	}
	
	
	public void createReportFile(){
		createDocument();
		writeInFile();
	}
	
	private void createFolder(String path){
		File newFolder=new File(path);
		if (!newFolder.exists()&& !newFolder.isDirectory()) {
			newFolder.mkdir();
		}
	}
	
	private void createFile(String path){
		File newFile=new File(path);
		if(!newFile.exists())    
		{    
		    try {    
		    	newFile.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		}    
	}


	
	private void createDocument(){
		this.document = DocumentHelper.createDocument();  
		Element root = DocumentHelper.createElement("SummaryReport");
		document.setRootElement(root);  
		
		String reportDateString=summaryReport.getReportDateString();
		Element ereportDate=root.addElement("ReportDate");	
		ereportDate.setText(reportDateString);
		
		Element esummarys=root.addElement("Summarys");
		for(int i=0;i<summaryServices.size();i++){
			SummaryService summaryService=summaryServices.get(i);
			
			String providerName=summaryService.getProviderName();
			String providerNumber=summaryService.getProviderNumber();
			int providerConsultCount=summaryService.getConsultCount();
			double providerSumFee=summaryService.getSumFee();
			
			Element esummary=esummarys.addElement("Summary");
			Element eproviderName=esummary.addElement("ProviderName");
			Element eproviderNumber=esummary.addElement("ProviderNumber");
			Element eproviderConsultCount=esummary.addElement("ConsultCount");
			Element eproviderSumFee=esummary.addElement("SumFee");
			
			eproviderName.setText(providerName);
			eproviderNumber.setText(providerNumber);
			eproviderConsultCount.setText(String.valueOf(providerConsultCount));
			eproviderSumFee.setText(String.valueOf(providerSumFee));
		}	
		
		int providerCount=summaryReport.getProviderCount();
		int consultCount=summaryReport.getConsultNoCount();
		double sumFee=summaryReport.getSumFee();
		
		Element eproviderCount=root.addElement("ProviderCount");
		Element econsultCount=root.addElement("ConsultCount");
		Element esumFee=root.addElement("SumFee");
		
		eproviderCount.setText(String.valueOf(providerCount));
		econsultCount.setText(String.valueOf(consultCount));
		esumFee.setText(String.valueOf(sumFee));
	}
	
	private void writeInFile(){	
		try {
			FileOutputStream out= new FileOutputStream(getFilePath());      
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        format.setEncoding("utf-8"); 
	        XMLWriter writer;
	        writer = new XMLWriter(out,format);
			writer.write(document);
	        writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ArrayList<SummaryService> getSummaryServices() {
		return summaryServices;
	}

	public void setSummaryServices(ArrayList<SummaryService> summaryServices) {
		this.summaryServices = summaryServices;
	}

	public SummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}
