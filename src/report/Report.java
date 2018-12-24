package report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import object.Person;

public abstract class Report {
	private static final String DATE_FORMAT = "MM-dd-yyyy";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Person person; 
	private Date reportDate;
	private String number;   
	private String name;   
	private String address;   
	private String city ;  
	private String country;  
	private String zip;  
	
	public Report(Person person){
		this.setPerson(person);
		reportDate=new Date();
		this.number=person.getNumber();
		this.name=person.getName();
		this.address=person.getAddress();
		this.city=person.getCity();
		this.country=person.getCountry();
		this.zip=person.getZip();
	}

	public Report(Person person,Date reportDate){
		this.setPerson(person);
		this.reportDate=reportDate;
		this.number=person.getNumber();
		this.name=person.getName();
		this.address=person.getAddress();
		this.city=person.getCity();
		this.country=person.getCountry();
		this.zip=person.getZip();
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public String getReportDateString(){
		dateFormatter.applyPattern(DATE_FORMAT);
		String reportDateString = dateFormatter.format(reportDate);
		return reportDateString;
	}
	
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	public void setReportDate(String reportDateString) {
		try {
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			this.reportDate = dateFormatter.parse(reportDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
