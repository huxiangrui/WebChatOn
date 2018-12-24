package object;

import java.util.Date;

import user.Members;
import user.Providers;
import user.Services;

import java.text.SimpleDateFormat;

public class Claim 
{
	//成员变量
	private Date currentDate;
	private Date serviceDate;
	private String providerNumber;
	private String memberNumber;
	private String serviceCode;
	private String note;
	
	
	public static final int CODE_LENGTH = 6;
	public static final int NOTE_LENGTH = 100;
	
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";	
	private static final char SEPARATOR = '#';
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();

	public Claim( Date aServiceDate, String aProviderNumber, String aMemberNumber,String aServiceCode)
	{
		currentDate = new Date();
		setServiceDate(aServiceDate);		
		setProviderNumber(aProviderNumber);
		setMemberNumber(aMemberNumber);
		setServiceCode(aServiceCode);
		setNote("");
	}
	
	public Claim( Date aServiceDate, String aProviderNumber, String aMemberNumber,String aServiceCode,String aNote)
	{
		currentDate = new Date();
		setServiceDate(aServiceDate);		
		setProviderNumber(aProviderNumber);
		setMemberNumber(aMemberNumber);
		setServiceCode(aServiceCode);
		setNote(aNote);
	}
	
	public Claim( Date aCurrentDate,Date aServiceDate, String aProviderNumber, String aMemberNumber,String aServiceCode)
	{
		setCurrentDate(aCurrentDate);
		setServiceDate(aServiceDate);		
		setProviderNumber(aProviderNumber);
		setMemberNumber(aMemberNumber);
		setServiceCode(aServiceCode);
		setNote("");
	}
	
	public Claim( Date aCurrentDate,Date aServiceDate, String aProviderNumber, String aMemberNumber,String aServiceCode,String aNote)
	{
		setCurrentDate(aCurrentDate);
		setServiceDate(aServiceDate);		
		setProviderNumber(aProviderNumber);
		setMemberNumber(aMemberNumber);
		setServiceCode(aServiceCode);
		setNote(aNote);
	}
	
	public Claim( Date aServiceDate,Provider aProvider,Member aMember,Service aService){
		currentDate = new Date();
		setServiceDate(aServiceDate);		
		setProviderNumber(aProvider.getNumber());
		setMemberNumber(aMember.getNumber());
		setServiceCode(aService.getCode());
		setNote("");
	}
	
	public Claim( Date aServiceDate,Provider aProvider,Member aMember,Service aService,String aNote){
		currentDate = new Date();
		setServiceDate(aServiceDate);		
		setProviderNumber(aProvider.getNumber());
		setMemberNumber(aMember.getNumber());
		setServiceCode(aService.getCode());
		setNote(aNote);
	}
	
	public Claim( Date aCurrentDate, Date aServiceDate,Provider aProvider,Member aMember,Service aService){
		setCurrentDate(aCurrentDate);
		setServiceDate(aServiceDate);		
		setProviderNumber(aProvider.getNumber());
		setMemberNumber(aMember.getNumber());
		setServiceCode(aService.getCode());
		setNote("");
	}
	
	public Claim( Date aCurrentDate, Date aServiceDate,Provider aProvider,Member aMember,Service aService,String aNote){
		setCurrentDate(aCurrentDate);
		setServiceDate(aServiceDate);		
		setProviderNumber(aProvider.getNumber());
		setMemberNumber(aMember.getNumber());
		setServiceCode(aService.getCode());
		setNote(aNote);
	}
	
	public Date getCurrentDate()
	{
		return currentDate;
	}//getSubmissionDate
	
	public String getServiceCode()
	{
		return serviceCode;
	}
	
	public String getProviderNumber()
	{
		return providerNumber;
	}//getProviderNumber
	
	public String getMemberNumber()
	{
		return memberNumber;
	}//getMemberNumber
	
	public Date getServiceDate()
	{
		return serviceDate;
	}//getServiceDate	
	
	public String getNote(){
		return note;
	}
	
	public void setServiceCode(String aCode)
	{
		if (aCode == null || aCode.length() == 0)
			throw new IllegalArgumentException("A service code is required");
		else if (aCode.length() > CODE_LENGTH)
			throw new IllegalArgumentException("Service code may not be more than " 
				+ CODE_LENGTH + " digits");
		else
			for (int i = 0; i < aCode.length(); i++)
				if (!Character.isDigit(aCode.charAt(i)))
					throw new IllegalArgumentException("Service code must consist " 
							+ "of digits only");
		serviceCode = aCode;
	}//setServiceCode
	
	public void setNote(String aNote)
	{
		if (aNote.length() > NOTE_LENGTH)
			throw new IllegalArgumentException("Note may not be more than " 
				+ CODE_LENGTH + " digits");
		else{
			note=aNote;
		}
	}
	
	public void setProviderNumber(String aNumber)
	{
		providerNumber = aNumber;
	}//setProviderNumber
	
	public void setMemberNumber(String aNumber)
	{
		memberNumber = aNumber;
	}//setMemberNumber
	
	public void setCurrentDate(Date aDate)
	{
		if (aDate == null)
			throw new IllegalArgumentException("The current date is required");
		else currentDate = aDate;
	}//setServiceDate	
	
	public void setServiceDate(Date aDate)
	{
		if (aDate == null)
			throw new IllegalArgumentException("The service date is required");
		else serviceDate = aDate;
	}
	
	public String toString()
	{
		dateFormatter.applyPattern(DATE_TIME_FORMAT);
		String currentDateString = dateFormatter.format(currentDate);
		dateFormatter.applyPattern(DATE_FORMAT);
		String serviceDateString = dateFormatter.format(serviceDate);
		return currentDateString + SEPARATOR
			+ serviceDateString + SEPARATOR
			+ providerNumber + SEPARATOR
			+ memberNumber + SEPARATOR
			+ serviceCode + SEPARATOR
			+ note;
	}
	
    public String toFormattedString()
    {
    	dateFormatter.applyPattern(DATE_TIME_FORMAT);
		String currentDateString = dateFormatter.format(currentDate);
		dateFormatter.applyPattern(DATE_FORMAT);
		String serviceDateString = dateFormatter.format(serviceDate);
    	String claimString   = "currentDate:" + currentDateString
    			      		  + "\nserviceDate:" + serviceDateString 
    			      		  + "\nproviderNumber:" + providerNumber
    			     		  + "\nmemberNumber:" + memberNumber
    			      		  + "\nserviceCode:" + serviceCode
    			      		  + "\nnote:" + note;
    	return claimString;
    }

	public Member getMember() {
		Members members=new Members();
		Member member=members.search(memberNumber);
		return member;
	}

	public Provider getProvider() {
		Providers providers=new Providers();
		Provider provider=providers.search(providerNumber);
		return provider;
	}


	public Service getService() {
		Services services=new Services();
		Service service=services.find(serviceCode);
		return service;
	}

}
