package user;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import object.Member;
import path.Path;

public class Members {

	
	private String filepath=Path.filepath+"/WebRoot/user/Member/Members.xml";
	private String personType="Member";
	
	//成员变量
	private Document document;
	private Element root;
	private ArrayList<Member> memberList;
	private ArrayList<String> numberList;
	
	public Members() 
	{
		memberList = new ArrayList<Member>();
		numberList = new ArrayList<String>(); 
		init();
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
    		    	Element emember = elementList.get(i);
    		    	String number=emember.attributeValue("number");
    		    	String name=emember.element("name").getText();
    		    	String address=emember.element("address").getText();
    		    	String city=emember.element("city").getText();
    		    	String country=emember.element("country").getText();
    		    	String zip=emember.element("zip").getText();
    		    	String email=emember.element("email").getText();
    		    	String status=emember.element("status").getText();
    		    	
    		    	Member member=new Member(number,name,address,city,country,zip,email,status);
    		    	memberList.add(member);
    		    	numberList.add(number);
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

	public Member search(String number){
		Member member = null;
		for(int i=0;i<memberList.size();i++){
			if(numberList.get(i).equals(number)){
				member=memberList.get(i);
			}
		}
		return member;
	}
	
	public ArrayList<Member> getAllMember(){
		return memberList;
	}
		
	public boolean add(Member member){ 
		String number=member.getNumber();   
		if(numberList.contains(number)){
			return update(member);
		}
		String name=member.getName();   
		String address=member.getAddress();   
		String city=member.getCity();  
		String country=member.getCountry();  
		String zip=member.getZip();  
		String email=member.getEmail();  
		String status=member.getStatus();
		
		Element emember = DocumentHelper.createElement(personType);
		emember.addAttribute("number", String.valueOf(number));

		Element ename=emember.addElement("name");
		Element eaddress=emember.addElement("address");
		Element ecity=emember.addElement("city");
		Element ecountry=emember.addElement("country");
		Element ezip=emember.addElement("zip");
		Element eemail=emember.addElement("email");	
		Element estatus=emember.addElement("status");	

		ename.setText(name);
		eaddress.setText(address);
		ecity.setText(city);
		ecountry.setText(country);
		ezip.setText(zip);
		eemail.setText(email);
		estatus.setText(status);

		root.add(emember);
		writeInFile();
		init();
		return true;
	}
	
	public boolean remove(String number){
		if(!numberList.contains(number)){
			return false;
		}
		else{
			String xpath="/Members/Member[@number="+number+"]";
			Node member = document.selectSingleNode(xpath);
			root.remove(member);
			writeInFile();
			init();
			return true;
		}
	}
	
	public boolean remove(Member member){
		String number=member.getNumber();
		return remove(number);
	}
	
	public boolean update(Member member){
		if(remove(member)){
			return add(member);
		}
		else{
			return false;
		}
	}
}
