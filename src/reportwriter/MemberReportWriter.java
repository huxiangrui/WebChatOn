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

import report.MemberReport;
import reportservice.MemberService;

public class MemberReportWriter extends ReportWriter{
	private static final String personType="member";
	
	//成员变量
	private Document document;
	private MemberReport memberReport;
	private ArrayList<MemberService> memberServices;
	
	public MemberReportWriter(MemberReport memberReport) {
		super(memberReport,personType);
		// TODO Auto-generated constructor stub
		this.memberReport=memberReport;
		this.memberServices=memberReport.getMemberServices();
		createReportFile();
	}
	
	public void createReportFile(){
		createDocument();
		writeInFile();
	}

	
	private void createDocument(){
		this.document = DocumentHelper.createDocument();  
		Element root = DocumentHelper.createElement("MemberReport");
		document.setRootElement(root);  
		
		String reportDateString=memberReport.getReportDateString();
		String number=memberReport.getNumber();   
		String name=memberReport.getName();   
		String address=memberReport.getAddress();   
		String city=memberReport.getCity();  
		String country=memberReport.getCountry();  
		String zip=memberReport.getZip();  
		
		Element ereportDate=root.addElement("ReportDate");
		Element ename=root.addElement("MemberName");
		Element enumber=root.addElement("MemberNumber");
		Element eaddress=root.addElement("MemberAddress");
		Element ecity=root.addElement("MemberCity");
		Element ecountry=root.addElement("MemberCountry");
		Element ezip=root.addElement("MemberZip");
		
		ereportDate.setText(reportDateString);
		ename.setText(name);
		enumber.setText(number);
		eaddress.setText(address);
		ecity.setText(city);
		ecountry.setText(country);
		ezip.setText(zip);
		
		Element eservices=root.addElement("Services");
		for(int i=0;i<memberServices.size();i++){
			MemberService memberService=memberServices.get(i);
			String serviceDateString=memberService.getServiceDateString();
			String providerName=memberService.getProviderName();
			String serviceName=memberService.getServiceName();
			
			Element eservice=eservices.addElement("Service");
			Element eserviceDate=eservice.addElement("ServiceDate");
			Element eproviderName=eservice.addElement("ProviderName");
			Element eserviceName=eservice.addElement("ServiceName");
			
			eserviceDate.setText(serviceDateString);
			eproviderName.setText(providerName);
			eserviceName.setText(serviceName);
		}			
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
