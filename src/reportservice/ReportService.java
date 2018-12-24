package reportservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import object.Claim;

public abstract class ReportService {
	private static final String DATE_FORMAT = "MM-dd-yyyy";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Date serviceDate;
	
	public ReportService(Date serviceDate){
		setServiceDate(serviceDate);
	}
	
	public ReportService(Claim claim){
		setServiceDate(claim.getServiceDate());
	}

	public Date getServiceDate() {
		return serviceDate;
	}
	
	public String getServiceDateString(){
		dateFormatter.applyPattern(DATE_FORMAT);
		String serviceDateString = dateFormatter.format(serviceDate);
		return serviceDateString;
	}
	
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	public void setServiceDate(String serviceDateString) {
		try {
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			this.serviceDate = dateFormatter.parse(serviceDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
