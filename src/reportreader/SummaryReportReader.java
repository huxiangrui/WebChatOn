package reportreader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import path.Path;
import report.SummaryReport;
import reportservice.SummaryService;

public class SummaryReportReader {
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	private static final String BASE_PATH=Path.filepath+"/WebRoot/report";
	private static final String DIRECTORY_PATH=BASE_PATH+"/summary";
	
	//成员变量
	private Document document;
	private SAXReader reader;
	private Element root;
	private Date reportDate;
	private SummaryReport summaryReport;
	private ArrayList<SummaryService> summaryServices;
	private String filePath;
	private File file;
	
	public SummaryReportReader(File file){
		setFile(file);
		this.filePath=file.getPath();
		summaryServices=new ArrayList<SummaryService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
	    	getReport();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SummaryReportReader(String filePath){
		setFilePath(filePath);
		setFile(new File(getFilePath()));
		summaryServices=new ArrayList<SummaryService>();
		summaryServices=new ArrayList<SummaryService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
	    	getReport();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SummaryReportReader(Date reportDate){
		dateFormatter.applyPattern(DATE_FORMAT);
		String reportDateString = dateFormatter.format(reportDate);
		setFilePath(DIRECTORY_PATH+"/summary-"+reportDateString+".xml");
		setFile(new File(getFilePath()));
		summaryServices=new ArrayList<SummaryService>();
		summaryServices=new ArrayList<SummaryService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
	    	getReport();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getReport(){
		try {
			Element ereportDate=root.element("ReportDate");
			String reportDateString= ereportDate.getText();
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			setReportDate(dateFormatter.parse(reportDateString));
			
			Element esummarys=root.element("Summarys");
			getSummaryServices(esummarys);
			
			Element eproviderCount=root.element("ProviderCount");
			Element etotalCount=root.element("ConsultCount");
			Element etotalFee=root.element("SumFee");
			
			int providerCount=Integer.valueOf(eproviderCount.getText());
			int totalCount=Integer.valueOf(etotalCount.getText());
			double totalFee=Double.valueOf(etotalFee.getText());
			
			this.summaryReport=new SummaryReport(getReportDate(), summaryServices, providerCount, totalCount, totalFee);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getSummaryServices(Element eservices){
		List<?> eserviceList=eservices.elements();
		for(int i=0;i<eserviceList.size();i++){
			Element eservice=(Element)eserviceList.get(i);
			
			Element eproviderName=eservice.element("ProviderName");
			Element eproviderNumber=eservice.element("ProviderNumber");
			Element eproviderConsultCount=eservice.element("ConsultCount");
			Element eproviderSumFee=eservice.element("SumFee");
			
			String providrName=eproviderName.getText();
			String providerNumber=eproviderNumber.getText();
			int providerConsultCount=Integer.valueOf(eproviderConsultCount.getText());
			double providerSumFee=Double.valueOf(eproviderSumFee.getText());
			
			SummaryService summaryService=new SummaryService(getReportDate(),providrName, providerNumber, providerConsultCount, providerSumFee);
			summaryServices.add(summaryService);
		}
	}
	
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
		if(!file.exists())    
		{    
		    try {    
		    	file.createNewFile();    
		    } catch (IOException e) {    
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }    
		}    
		this.file = file;
	}

	public SummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}
}
