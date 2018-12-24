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

import object.Provider;
import report.ProviderReport;
import reportservice.ProviderService;
import user.Providers;

public class ProviderReportReader extends ReportReader {
	private static final String personType="provider";
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Document document;
	private SAXReader reader;
	private Element root;
	private ProviderReport providerReport;
	private ArrayList<ProviderService> providerServices;

	public ProviderReportReader(Provider provider,Date reportDate){
		super(provider,reportDate,personType);
		providerServices=new ArrayList<ProviderService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ProviderReportReader(File file){
		super(file);
		providerServices=new ArrayList<ProviderService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ProviderReportReader(String filepath){
		super(filepath);
		providerServices=new ArrayList<ProviderService>();	
    	try {
    		this.reader = new SAXReader();  
			this.document = reader.read(getFilePath());	
	    	this.root=document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public ProviderReport getProviderReport(){
		try {
			Element enumber=root.element("ProviderNumber");
			Element ereportDate=root.element("ReportDate");
			Element etotalCount=root.element("ConsultCount");
			Element etotalFee=root.element("TotalFee");
			
			String number=enumber.getText();
			String reportDateString= ereportDate.getText();
			
			int totalCount=Integer.valueOf(etotalCount.getText());
			double totalFee=Double.valueOf(etotalFee.getText());
			
			Providers providers=new Providers();
			Provider provider=providers.search(number);
			
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			Date reportDate = dateFormatter.parse(reportDateString);
			Element eservices=root.element("Services");
			getProviderServices(eservices);
			
			providerReport=new ProviderReport(provider, reportDate, providerServices, totalCount, totalFee);
			return providerReport;	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return providerReport;
	}
	
	private void getProviderServices(Element eservices){
		List<?> eserviceList=eservices.elements();
		for(int i=0;i<eserviceList.size();i++){
			try {
				Element eservice=(Element)eserviceList.get(i);
				
				Element eserviceDate=eservice.element("ServiceDate");
				Element ecurrentDate=eservice.element("SubmitDate");
				Element ememberName=eservice.element("MemberName");
				Element ememberNumber=eservice.element("MemberNumber");
				Element eserviceCode=eservice.element("ServiceCode");
				Element efee=eservice.element("ServiceFee");
				
				String serviceDateString=eserviceDate.getText();
				String currentDateString=ecurrentDate.getText();
				String memberName=ememberName.getText();
				String memberNumber=ememberNumber.getText();
				String serviceCode=eserviceCode.getText();
				double fee=Double.valueOf(efee.getText());
				

				dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT); 
				Date currentDate = dateFormatter.parse(currentDateString);
				dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
				Date serviceDate = dateFormatter.parse(serviceDateString);
				ProviderService providerService=
						new ProviderService(currentDate, serviceDate, memberName, memberNumber, serviceCode, fee);
				providerServices.add(providerService);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
