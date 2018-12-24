package reportservice;

import java.util.Date;

import object.Provider;
import report.ProviderReport;

public class SummaryService{
	//成员变量
	private Date reportDate;
	private String providerName;
	private String providerNumber;
	private int consultCount;
	private double sumFee;
	
	public SummaryService(Date reportDate,Provider provider){
		setReportDate(reportDate);
		setProviderName(provider.getName());
		setProviderNumber(provider.getNumber());
		
		ProviderReport providerReport=new ProviderReport(provider,reportDate);
		setConsultCount(providerReport.getTotalCount());
		setSumFee(providerReport.getTotalFee());	
	}
	
	public SummaryService(Date reportDate,String providerName,String providerNumber,int consultCount,double sumFee){
		setProviderName(providerName);
		setProviderNumber(providerNumber);
		setConsultCount(consultCount);
		setSumFee(sumFee);	
	}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderNumber() {
		return providerNumber;
	}
	public void setProviderNumber(String providerNumber) {
		this.providerNumber = providerNumber;
	}
	public int getConsultCount() {
		return consultCount;
	}
	public void setConsultCount(int consultCount) {
		this.consultCount = consultCount;
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

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
}
