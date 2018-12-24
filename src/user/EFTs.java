package user;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import object.EFT;
import path.Path;
import reportservice.SummaryService;

public class EFTs {
	private static final String DATE_FORMAT = "MM-dd-yyyy";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	private String filepath=Path.filepath+"/WebRoot/user/EFT/EFTs.xml";
	private String personType="EFT";
	
	//成员变量
	private Document document;
	private Element root;
	private ArrayList<EFT> EFTList;
	private ArrayList<String> reportDateStringList;	
	
	public EFTs(){
		EFTList=new ArrayList<EFT>();
		reportDateStringList=new ArrayList<String>();
		init();
	}

	
	private EFT getEFTFromElement(Element eEFT){	
		try {	
	    	String reportDateString=eEFT.element("ReportDate").getText();
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			Date reportDate = dateFormatter.parse(reportDateString);
			
			reportDateStringList.add(reportDateString);
			
			return new EFT(reportDate);		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void init(){ 
        try {
        	SAXReader reader = new SAXReader();  
        	document = reader.read(filepath);
        	root=document.getRootElement();
        	List<?> elementList=root.elements(); 
    		if(elementList.size()>0){
    		    for(int i=0;i<elementList.size();i++) {
    				Element eEFT = (Element) elementList.get(i);
    				EFT eft=getEFTFromElement(eEFT);
    				EFTList.add(eft);
    		    }	
    		}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void writeInFile(){
		
		try {
			FileOutputStream out= new FileOutputStream(filepath);      
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

	public boolean add(EFT eft){
		
		Date reportDate = eft.getReportDate();
		dateFormatter.applyPattern(DATE_FORMAT);
		String reportDateString = dateFormatter.format(reportDate);
		
		if(reportDateStringList.contains(reportDateString)){
			remove(reportDateString);
		}
		
		Element eEFT=root.addElement(personType);

		Element ereportDate = eEFT.addElement("ReportDate");
		ereportDate.setText(reportDateString);
		
		Element eproviders=eEFT.addElement("Providers");
		ArrayList<SummaryService> summaryServices=eft.getSummaryServices();
		for(int i=0;i<summaryServices.size();i++){
			SummaryService summaryService=summaryServices.get(i);
			
			String providerName=summaryService.getProviderName();
			String providerNumber=summaryService.getProviderNumber();
			double providerSumFee=summaryService.getSumFee();
			
			Element eprovider=eproviders.addElement("Provider");
			Element eproviderName=eprovider.addElement("ProviderName");
			Element eproviderNumber=eprovider.addElement("ProviderNumber");
			Element eproviderSumFee=eprovider.addElement("TotalFee");
			
			eproviderName.setText(providerName);
			eproviderNumber.setText(providerNumber);
			eproviderSumFee.setText(String.valueOf(providerSumFee));
		}
		
		writeInFile();
		init();
		return true;
	}
	
	public EFT find(Date date)
	{
		dateFormatter.applyPattern(DATE_FORMAT);
		String reportDateString = dateFormatter.format(date);
		EFT eft = null;
		for(int i=0;i<reportDateStringList.size();i++){
			if(reportDateStringList.contains(reportDateString)){
				eft=EFTList.get(i);
			}
		}
		return eft;
	}
	
	private boolean remove(String reportDateString){
		if(!reportDateStringList.contains(reportDateString)){
			return false;
		}
		else{
			String xpath="/EFTs/EFT[ReportDate="+reportDateString+"]";
			Element eft = (Element)document.selectSingleNode(xpath);
			root.remove(eft);
			writeInFile();
			init();
			return true;
		}
	}
	
	public ArrayList<EFT> findAll(){
		return EFTList;
	}
}
