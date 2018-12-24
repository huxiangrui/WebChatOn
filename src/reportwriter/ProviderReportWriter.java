package reportwriter;

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

import report.ProviderReport;
import reportservice.ProviderService;

public class ProviderReportWriter extends ReportWriter{
	private static final String personType="provider";
	
	//成员变量
	private Document document;
	private ProviderReport providerReport;
	private ArrayList<ProviderService> providerServices;
	
	public ProviderReportWriter(ProviderReport providerReport) {
		super(providerReport,personType);
		// TODO Auto-generated constructor stub
		
		this.providerReport=providerReport;
		providerServices=providerReport.getProviderServices();
	}
	
	public void createReportFile(){
		createDocument();
		writeInFile();
	}
	
	private void createDocument(){
		this.document = DocumentHelper.createDocument();  
		Element root = DocumentHelper.createElement("ProviderReport");
		document.setRootElement(root);  
		
		String reportDateString=providerReport.getReportDateString();
		String number=providerReport.getNumber();   
		String name=providerReport.getName();   
		String address=providerReport.getAddress();   
		String city=providerReport.getCity();  
		String country=providerReport.getCountry();  
		String zip=providerReport.getZip();  
		
		Element ereportDate=root.addElement("ReportDate");
		Element ename=root.addElement("ProviderName");
		Element enumber=root.addElement("ProviderNumber");
		Element eaddress=root.addElement("ProviderAddress");
		Element ecity=root.addElement("ProviderCity");
		Element ecountry=root.addElement("ProviderCountry");
		Element ezip=root.addElement("ProviderZip");
		
		ereportDate.setText(reportDateString);
		ename.setText(name);
		enumber.setText(number);
		eaddress.setText(address);
		ecity.setText(city);
		ecountry.setText(country);
		ezip.setText(zip);
		
		Element eservices=root.addElement("Services");
		for(int i=0;i<providerServices.size();i++){
			ProviderService providerService=providerServices.get(i);
			String serviceDateString=providerService.getServiceDateString();
			String currentDateString=providerService.getCurrentDateString();
			String memberNumber=providerService.getMemberNumber();
			String memberName=providerService.getMemberName();
			String serviceCode=providerService.getServiceCode();
			double fee=providerService.getFee();
			
			Element eservice=eservices.addElement("Service");
			
			Element eserviceDate=eservice.addElement("ServiceDate");
			Element ecurrentDate=eservice.addElement("SubmitDate");
			Element ememberNumber=eservice.addElement("MemberNumber");
			Element ememberName=eservice.addElement("MemberName");
			Element eserviceCode=eservice.addElement("ServiceCode");
			Element efee=eservice.addElement("ServiceFee");
			
			eserviceDate.setText(serviceDateString);
			ecurrentDate.setText(currentDateString);
			ememberName.setText(memberName);
			ememberNumber.setText(memberNumber);
			eserviceCode.setText(serviceCode);
			efee.setText(String.valueOf(fee));
		}	
		
		int totalCount=providerReport.getTotalCount();
		double totalFee=providerReport.getTotalFee();
		
		Element etotalCount=root.addElement("ConsultCount");
		Element etotalFee=root.addElement("TotalFee");
		
		etotalCount.setText(String.valueOf(totalCount));
		etotalFee.setText(String.valueOf(totalFee));
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

}
