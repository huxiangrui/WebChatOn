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
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import object.Provider;
import path.Path;

public class Providers {
	private String filepath=Path.filepath+"/WebRoot/user/Provider/Providers.xml";
	private String personType="Provider";
	
	//成员变量
	private Document document;
	private Element root;
	private ArrayList<Provider> providerList;
	private ArrayList<String> numberList;
	
	public Providers() 
	{
		providerList = new ArrayList<Provider>();
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
    		    	Element eprovider = elementList.get(i);
    		    	String number=eprovider.attributeValue("number");
    		    	String name=eprovider.element("name").getText();
    		    	String address=eprovider.element("address").getText();
    		    	String city=eprovider.element("city").getText();
    		    	String country=eprovider.element("country").getText();
    		    	String zip=eprovider.element("zip").getText();
    		    	String email=eprovider.element("email").getText();
    		    	String type=eprovider.element("type").getText();
    		    	
    		    	Provider provider=new Provider(number,name,address,city,country,zip,email,type);
    		    	providerList.add(provider);
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
			System.out.println("写入成功");
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

	public Provider search(String number){
		Provider provider = null;
		for(int i=0;i<providerList.size();i++){
			if(numberList.contains(number)){
				provider=providerList.get(i);
			}
		}
		return provider;
	}
	
	public ArrayList<Provider> getAllProvider(){
		return providerList;
	}
		
	public boolean add(Provider provider){ 
		String number=provider.getNumber();   
		if(numberList.contains(number)){
			return update(provider);
		}
		String name=provider.getName();   
		String address=provider.getAddress();   
		String city=provider.getCity();  
		String country=provider.getCountry();  
		String zip=provider.getZip();  
		String email=provider.getEmail();  
		String type=provider.getType();
		
		Element eprovider = DocumentHelper.createElement(personType);
		eprovider.addAttribute("number", String.valueOf(number));

		Element ename=eprovider.addElement("name");
		Element eaddress=eprovider.addElement("address");
		Element ecity=eprovider.addElement("city");
		Element ecountry=eprovider.addElement("country");
		Element ezip=eprovider.addElement("zip");
		Element eemail=eprovider.addElement("email");	
		Element etype=eprovider.addElement("type");	

		ename.setText(name);
		eaddress.setText(address);
		ecity.setText(city);
		ecountry.setText(country);
		ezip.setText(zip);
		eemail.setText(email);
		etype.setText(type);

		root.add(eprovider);
		writeInFile();
		init();//刷新
		return true;
	}
	
	public boolean remove(String number){
		if(!numberList.contains(number)){
			return false;
		}
		else{
			String xpath="/Providers/Provider[@number="+number+"]";
			Element provider = (Element)document.selectSingleNode(xpath);
			root.remove(provider);
			writeInFile();
			init();
			return true;
		}
	}
	
	public boolean remove(Provider provider){
		String number=provider.getNumber();
		return remove(number);
	}
	
	public boolean update(Provider provider){
		if(remove(provider)){
			return add(provider);
		}
		else{
			return false;
		}
	}
	
	public void updateAll(ArrayList<Provider> plist){
		ArrayList<Provider> providerlist=getAllProvider();
		for(Provider p:providerlist){
			remove(p);
		}
		for(Provider provider:plist){
			add(provider);
		}
	}
}
