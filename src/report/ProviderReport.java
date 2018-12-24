package report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import object.Provider;
import reportservice.ProviderService;
import object.Claim;
import user.Claims;
import user.Providers;

public class ProviderReport extends Report{
	private static final double MAX_TOTAL_FEE=100000.00;

	//成员变量
	private int totalCount;
	private double totalFee;
	private ArrayList<ProviderService> providerServices;

	public ProviderReport(Provider provider) {
		super(provider);
		// TODO Auto-generated constructor stub
		setProviderServices();
		setTotalCount();
		setTotalFee();
	}
	
	public ProviderReport(Provider provider,Date reportDate) {
		super(provider,reportDate);
		// TODO Auto-generated constructor stub
		setProviderServices();
		setTotalCount();
		setTotalFee();
	}

	public ProviderReport(Provider provider,Date reportDate,ArrayList<ProviderService> providerServices
			,int totalCount,double totalFee) {
		super(provider,reportDate);
		// TODO Auto-generated constructor stub
		setProviderServices(providerServices);
		setTotalCount(totalCount);
		setTotalFee(totalFee);
	}
	
	public ArrayList<ProviderService> getProviderServices() {
		return providerServices;
	}
	
	public void setProviderServices(ArrayList<ProviderService> providerServices){
		this.providerServices=providerServices;
	}

	private void setProviderServices() {
		ArrayList<ProviderService> providerservices= new ArrayList<ProviderService>();
		
		Date endDate=getReportDate();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_YEAR,-7);
		Date startDate=calendar.getTime();
		
		Claims claims=new Claims();
		ArrayList<Claim> claimsListByProvider = claims.findByProvider(getNumber());
		
		for (Claim claim : claimsListByProvider){
			if (claim.getCurrentDate().after(startDate)
					&& claim.getCurrentDate().before(endDate)){
				ProviderService providerService=new ProviderService(claim);
				providerservices.add(providerService);
			}
		}
		this.providerServices = providerservices;
	}

	public int getTotalCount() {
		return totalCount;
	}

	private void setTotalCount(){
		setTotalCount(providerServices.size());
	}
	
	public void setTotalCount(int totalCount) {
		if(totalCount>999){
			totalCount=999;
			return;
		}
		this.totalCount = totalCount;
	}

	public double getTotalFee() {
		return totalFee;
	}

	private void setTotalFee(){
		double totalfee=0;
		for(int i=0;i<providerServices.size();i++){
			double fee=providerServices.get(i).getFee();
			totalfee+=fee;
		}
		setTotalFee(totalfee);
	}
	
	public void setTotalFee(double totalFee) {
		if(totalFee>=MAX_TOTAL_FEE){
			this.totalFee=99999.99;
			return;
		}	
		this.totalFee = totalFee;
	}
	
	public Provider getProvider() {
		Providers providers=new Providers();
		Provider provider=providers.search(getNumber());
		return provider;
	}
}
