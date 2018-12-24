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
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import object.Claim;
import object.Member;
import object.Provider;
import path.Path;


public class Claims
{ 
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	private String filepath=Path.filepath+"/WebRoot/user/Claim/Claims.xml";
	private String personType="Claim";
	
	//成员变量
	private Document document;
	private Element root;
	private ArrayList<Claim> claimList;

	public Claims() 
	{
		claimList = new ArrayList<Claim>();
		init();
	}
	
	private Claim getClaimFromElement(Element eclaim){	
		try {
			Date serviceDate;
	    	String currentDateString=eclaim.element("currentDate").getText();
	    	String serviceDateString=eclaim.element("serviceDate").getText();
	    	String providerNumber=eclaim.element("providerNumber").getText();
	    	String memberNumber=eclaim.element("memberNumber").getText();
	    	String serviceCode=eclaim.element("serviceCode").getText();
	    	dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);  
			Date currentDate = dateFormatter.parse(currentDateString);
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			serviceDate = dateFormatter.parse(serviceDateString);
			String note=eclaim.element("note").getText();;
			if(note==null){
				note="";
			}

			Claim claim=new Claim(currentDate,serviceDate,providerNumber,memberNumber,serviceCode,note);
			return claim;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void init(){ 
        try {
        	SAXReader reader = new SAXReader();  
        	document = reader.read(filepath);
        	root=document.getRootElement();
        	List<Element> elementList=root.elements(); 
    		if(elementList.size()>0){
    		    for(int i=0;i<elementList.size();i++) {
    				Element eclaim = elementList.get(i);
    				Claim claim=getClaimFromElement(eclaim);
					claimList.add(claim);
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
	
	public boolean add(Claim claim)
	{
		Date currentDate = claim.getCurrentDate();
		Date serviceDate = claim.getServiceDate();
    	String providerNumber=claim.getProviderNumber();
    	String memberNumber=claim.getMemberNumber();
    	String serviceCode=claim.getServiceCode();
    	String note=claim.getNote();
    	
    	dateFormatter.applyPattern(DATE_TIME_FORMAT);
		String currentDateString = dateFormatter.format(currentDate);
		dateFormatter.applyPattern(DATE_FORMAT);
		String serviceDateString = dateFormatter.format(serviceDate);
		
		Element eclaim = DocumentHelper.createElement(personType);
		
		Element ecurrentDate = eclaim.addElement("currentDate");
		Element eserviceDate = eclaim.addElement("serviceDate");
		Element eproviderNumber = eclaim.addElement("providerNumber");
		Element ememberNumber = eclaim.addElement("memberNumber");
		Element eserviceCode = eclaim.addElement("serviceCode");
		Element enote = eclaim.addElement("note");
		
		if(!(note==null)){
			note="";		
		}
		
		enote.setText(note);
		ecurrentDate.setText(currentDateString);
		eserviceDate.setText(serviceDateString);
		eproviderNumber.setText(providerNumber);
		ememberNumber.setText(memberNumber);
		eserviceCode .setText(serviceCode );

		root.add(eclaim);
		writeInFile();
		init();
		return true;
	}
	
	public ArrayList<Claim> find(String name,String value)
	{
		ArrayList<Claim> claimlist=new ArrayList<Claim>();
		String xpath="/Claims/Claim["+name+"="+value+"]";
		List<?> lclaim = document.selectNodes(xpath);
		if(lclaim.size()>0){
			for(int i=0;i<lclaim.size();i++){
				Element eclaim=(Element) lclaim.get(i) ;
				Claim claim=getClaimFromElement(eclaim);
				claimlist.add(claim);
			}
		}	
		return claimlist;
	}
	
	public ArrayList<Claim> findAll(){
		ArrayList<Claim> claimlist=new ArrayList<Claim>();
		String xpath="/Claims/Claim";
		List<?> lclaim = document.selectNodes(xpath);
		if(lclaim.size()>0){
			for(int i=0;i<lclaim.size();i++){
				Element eclaim=(Element) lclaim.get(i) ;
				Claim claim=getClaimFromElement(eclaim);
				claimlist.add(claim);
			}
		}	
		return claimlist;
	}
	
	public ArrayList<Provider> findAllProvider(Date startDate,Date endDate){
		ArrayList<Provider> providerlist=new  ArrayList<Provider>();
		String xpath="/Claims/Claim";
		List<?> lclaim = document.selectNodes(xpath);
		if(lclaim.size()>0){
			for(int i=0;i<lclaim.size();i++){
				Element eclaim=(Element) lclaim.get(i) ;
				Claim claim=getClaimFromElement(eclaim);
				if (claim.getCurrentDate().after(startDate)
						&& claim.getCurrentDate().before(endDate)){
					Provider provider=claim.getProvider();
					if(!providerlist.contains(provider)){
						providerlist.add(provider);
					}
				}
			}
		}
		return providerlist;	
	}
	
	public ArrayList<Member> findAllMember(Date startDate,Date endDate){
		ArrayList<Member> memberlist=new  ArrayList<Member>();
		String xpath="/Claims/Claim";
		List<?> lclaim = document.selectNodes(xpath);
		if(lclaim.size()>0){
			for(int i=0;i<lclaim.size();i++){
				Element eclaim=(Element) lclaim.get(i) ;
				Claim claim=getClaimFromElement(eclaim);
				if (claim.getCurrentDate().after(startDate)
						&& claim.getCurrentDate().before(endDate)){
					Member member=claim.getMember();
					if(!memberlist.contains(member)){
						memberlist.add(member);
					}
				}
			}
		}
		return memberlist;	
	}
	
	public ArrayList<Claim> findByProvider(String providerNumber)
	{
		ArrayList<Claim> list=find("providerNumber",providerNumber);
		return list;
	}
	
	public ArrayList<Claim> findByMember(String memberNumber)
	{
		ArrayList<Claim> list=find("memberNumber",memberNumber);
		return list;
	}
	 
	public ArrayList<Claim> findByServiceCode(String serviceCode)
	{
		ArrayList<Claim> list=find("serviceCode",serviceCode);
		return list;
	}

}
