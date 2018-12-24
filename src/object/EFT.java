package object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import report.SummaryReport;
import reportservice.SummaryService;



public class EFT {
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
	//成员变量
	private Date reportDate;
	private SummaryReport summaryReport;
	private ArrayList<SummaryService> summaryServices;

	public EFT(){
		setReportDate(new Date());
		setSummaryReport(new SummaryReport(getReportDate()));
		setSummaryServices(getSummaryReport().getSummaryServiceList());
	}
	
	public EFT(Date reportDate){
		setReportDate(reportDate);
		setSummaryReport(new SummaryReport(getReportDate()));
		setSummaryServices(getSummaryReport().getSummaryServiceList());
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

	public SummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}

	public ArrayList<SummaryService> getSummaryServices() {
		return summaryServices;
	}

	public void setSummaryServices(ArrayList<SummaryService> summaryServices) {
		this.summaryServices = summaryServices;
	}
}
