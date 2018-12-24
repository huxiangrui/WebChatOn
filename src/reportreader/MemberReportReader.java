package reportreader;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import object.Member;
import report.MemberReport;
import reportservice.MemberService;
import user.Members;

public class MemberReportReader extends ReportReader{
	private static final String personType="member";
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Document document;
	private SAXReader reader;
	private Element root;
	private MemberReport memberReport;
	private ArrayList<MemberService> memberServices;

	public MemberReportReader(Member member,Date reportDate){
		super(member,reportDate,personType);
		memberServices=new ArrayList<MemberService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MemberReportReader(File file){
		super(file);
		memberServices=new ArrayList<MemberService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MemberReportReader(String filepath){
		super(filepath);
		memberServices=new ArrayList<MemberService>();
		memberServices=new ArrayList<MemberService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MemberReport getMemberReport(){
		try {
			Element enumber=root.element("MemberNumber");
			Element ereportDate=root.element("ReportDate");
			
			String number=enumber.getText();
			String reportDateString= ereportDate.getText();
			
			Members members=new Members();
			Member member=members.search(number);
			
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			Date reportDate = dateFormatter.parse(reportDateString);
			Element eservices=root.element("Services");
			getMemberServices(eservices);
			
			memberReport=new MemberReport(member,reportDate,memberServices);
			return memberReport;	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberReport;

	}
	
	private void getMemberServices(Element eservices){
		List<?> eserviceList=eservices.elements();
		for(int i=0;i<eserviceList.size();i++){
			try {
				Element eservice=(Element)eserviceList.get(i);
				
				Element eserviceDate=eservice.element("ServiceDate");
				Element eproviderName=eservice.element("ProviderName");
				Element eserviceName=eservice.element("ServiceName");
				
				String serviceDateString=eserviceDate.getText();
				String providerName=eproviderName.getText();
				String serviceName=eserviceName.getText();
				
				dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
				Date serviceDate = dateFormatter.parse(serviceDateString);
				
				MemberService memberService=new MemberService(serviceDate,providerName,serviceName); 
				memberServices.add(memberService);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
