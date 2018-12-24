package reportservice;

import java.util.Date;

import object.Claim;
import object.Provider;
import object.Service;

public class MemberService extends ReportService {
	
	//成员变量
	private String providerName;
	private String serviceName;
	
	public MemberService(Date serviceDate,String providerName,String serviceName){
		super(serviceDate);
		setProviderName(providerName);
		setServiceName(serviceName);
	}
	
	public MemberService(Claim claim){
		super(claim);
		
		Provider provider=claim.getProvider();
		Service service=claim.getService();
		
		this.providerName=provider.getName();
		this.serviceName=service.getName();
	}
	
	public String getProviderName() {
		return providerName;
	}
	
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
