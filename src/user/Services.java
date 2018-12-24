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

import object.Service;
import path.Path;

public class Services
{
	private String filepath=Path.filepath+"/WebRoot/user/Service/Services.xml";
	private String personType="Service";
	
	//成员变量
	private Document document;
	private Element root;
	private ArrayList<Service> serviceList;
	private ArrayList<String> codeList;
	
	public Services() 
	{
		serviceList = new ArrayList<Service>();
		codeList = new ArrayList<String>(); 
		init();
	}//default constructor

	private Service getServiceFromElement(Element eservice){
		String code=eservice.attributeValue("code");
		String name=eservice.element("name").getText();
		double fee=Double.valueOf(eservice.element("fee").getText());
		
		codeList.add(code);
	    Service service=new Service(code,name,fee);
		return service;
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
    				Element eservice = elementList.get(i);
    				Service service=getServiceFromElement(eservice);
    				serviceList.add(service);
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
	
	public Service find(String serviceCode)
	{
		String xpath="/Services/Service[@code="+serviceCode+"]";
		Node nservice = document.selectSingleNode(xpath);
		Element eservice=(Element) nservice;
		Service service=getServiceFromElement(eservice);
		return service;
	}

	public boolean add(Service service)
	{
		String code = service.getCode();
		String name = service.getName();
		double fee = service.getFee();
		
		if(codeList.contains(code)){
			return update(service);
		}
		
		Element eservice = DocumentHelper.createElement(personType);
		eservice.addAttribute("code", code);
		
		Element ename=eservice.addElement("name");
		Element efee=eservice.addElement("fee");
		
		ename.setText(name);
		efee.setText(String.valueOf(fee));
		root.add(eservice);
		writeInFile();
		init();//刷新
		return true;
	}//add
	
	/** Updates the given service in the collection.
	 *  @param aService the service to be updated
	 */
	public boolean update(Service service)
	{
		if(remove(service)){
			return add(service);
		}
		else{
			return false;
		}
	}//update
	
	/** Deletes the service with the given service number from the collection
	 *  @param serviceCode the code of the service to delete
	 */
	public boolean remove(String serviceCode)
	{
		if(!codeList.contains(serviceCode)){
			return false;
		}
		else{
			String xpath="/Services/Service[@code="+serviceCode+"]";
			Element eservice = (Element)document.selectSingleNode(xpath);
			root.remove(eservice);
			writeInFile();
			init();
			return true;
		}
	}
	 
	public boolean remove(Service service){
		String code=service.getCode();
		return remove(code);
	}
	
	public ArrayList<Service> getAll()
	{
		ArrayList<Service> servicelist=new ArrayList<Service>();
		String xpath="/Services/Service";
		List<?> nservicelist=document.selectNodes(xpath,"name");
		if(nservicelist.size()>0){
			
		    for(int i=0;i<nservicelist.size();i++) {
				Element eservice = (Element) nservicelist.get(i);
				Service service=getServiceFromElement(eservice);
				servicelist.add(service);
		    }	
		}

	 	return servicelist;
	}
	 
	

		
}