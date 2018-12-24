package report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import object.Provider;
import reportservice.SummaryService;
import user.Claims;

public class SummaryReport {
	private static final String DATE_FORMAT = "MM-dd-yyyy";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();

	private Claims claims=new Claims();
	
	
	//成员变量
	private Date reportDate;
	private ArrayList<SummaryService> summaryServiceList;
	private int providerCount;
	private int consultNoCount;
	private double sumFee;
	
	public SummaryReport(Date reportDate,ArrayList<SummaryService> summaryServiceList,
			int providerCount,int consultNoCount,double sumFee){
		setReportDate(reportDate);
		this.summaryServiceList=summaryServiceList;
		this.providerCount=providerCount;
		this.consultNoCount=consultNoCount;
		this.sumFee=sumFee;
	}
	
	public SummaryReport(){
		setReportDate(new Date());
		this.summaryServiceList=new ArrayList<SummaryService>();
		this.consultNoCount=0;
		this.sumFee=0;
		
		Date endDate=getReportDate();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_YEAR,-7);
		Date startDate=calendar.getTime();
		
		ArrayList<Provider> providerList=claims.findAllProvider(startDate, endDate);
		for(int i=0;i<providerList.size();i++){
			Provider provider=providerList.get(i);
			SummaryService summaryService=new SummaryService(getReportDate(),provider);
			consultNoCount+=summaryService.getConsultCount();
			sumFee=summaryService.getSumFee();
			summaryServiceList.add(summaryService);
		}
		
		this.providerCount=summaryServiceList.size();
	}
	
	public SummaryReport(Date reportDate){
		setReportDate(reportDate);
		this.summaryServiceList=new ArrayList<SummaryService>();
		this.consultNoCount=0;
		this.sumFee=0;
		
		Date endDate=getReportDate();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_YEAR,-7);
		Date startDate=calendar.getTime();
		
		ArrayList<Provider> providerList=claims.findAllProvider(startDate, endDate);
		for(int i=0;i<providerList.size();i++){
			Provider provider=providerList.get(i);
			SummaryService summaryService=new SummaryService(getReportDate(),provider);
			consultNoCount+=summaryService.getConsultCount();
			sumFee=summaryService.getSumFee();
			summaryServiceList.add(summaryService);
		}
		
		this.providerCount=summaryServiceList.size();
	}
	
	
	public int getProviderCount() {
		return providerCount;
	}
	public void setProviderCount(int providerCount) {
		this.providerCount = providerCount;
	}
	public int getConsultNoCount() {
		return consultNoCount;
	}
	public void setConsultNoCount(int consultNoCount) {
		this.consultNoCount = consultNoCount;
	}
	public double getSumFee() {
		return sumFee;
	}
	public void setSumFee(double sumFee) {
		this.sumFee = sumFee;
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

	public ArrayList<SummaryService> getSummaryServiceList() {
		return summaryServiceList;
	}

	public void setSummaryServiceList(ArrayList<SummaryService> summarySummaryList) {
		this.summaryServiceList = summarySummaryList;
	}
	
	
}
