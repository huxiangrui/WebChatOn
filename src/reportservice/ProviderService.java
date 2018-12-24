package reportservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import object.Claim;
import object.Member;
import object.Service;

public class ProviderService extends ReportService {
	private static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Date currentDate;
	private String memberName;
	private String memberNumber;
	private String serviceCode;
	private double fee;
	
	public ProviderService(Date serviceDate,Date currentDate,String memberName,String memberNumber,
			String serviceCode,double fee){
		super(serviceDate);
		setCurrentDate(currentDate);
		setMemberName(memberName);
		setMemberNumber(memberNumber);
		setServiceCode(serviceCode);	
		setFee(fee);
	}
	
	public ProviderService(Claim claim){
		super(claim);
		this.currentDate=claim.getCurrentDate();
		this.memberNumber=claim.getMemberNumber();
		this.serviceCode=claim.getServiceCode();
		
		Member member=claim.getMember();
		Service service=claim.getService();
		
		this.memberName=member.getName();
		this.fee=service.getFee();
	}
	

	public Date getCurrentDate() {
		return currentDate;
	}

	public String getCurrentDateString(){
		dateFormatter.applyPattern(DATE_TIME_FORMAT);
		String currentDateString = dateFormatter.format(currentDate);
		return currentDateString;
	}
	
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	public void setCurrentDate(String currentDateString) {
		try {
			dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);  
			this.currentDate = dateFormatter.parse(currentDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
}


