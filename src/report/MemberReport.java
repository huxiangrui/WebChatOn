package report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import object.Claim;
import object.Member;
import reportservice.MemberService;
import user.Claims;
import user.Members;

public class MemberReport extends Report {
	//成员变量
	private ArrayList<MemberService> memberServices;
		
	public MemberReport(Member member) {
		super(member);
		// TODO Auto-generated constructor stub
		setMemberServices();
	}
	
	public MemberReport(Member member,Date reportDate) {
		super(member,reportDate);
		// TODO Auto-generated constructor stub
		setMemberServices();
	}
	
	public MemberReport(Member member,Date reportDate,ArrayList<MemberService> memberServices) {
		super(member,reportDate);
		// TODO Auto-generated constructor stub
		setMemberServices(memberServices);
	}

	public ArrayList<MemberService> getMemberServices() {
		return memberServices;
	}
	
	private void setMemberServices() {
		ArrayList<MemberService> memberservices= new ArrayList<MemberService>();
		
		Date endDate=getReportDate();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_YEAR,-7);
		Date startDate=calendar.getTime();
		
		Claims claims=new Claims();
		ArrayList<Claim> claimsListByMember = claims.findByMember(getNumber());
		
		for (Claim claim : claimsListByMember){
			if (claim.getCurrentDate().after(startDate)
					&& claim.getCurrentDate().before(endDate)){
				MemberService memberService=new MemberService(claim);
				memberservices.add(memberService);
			}
		}
		this.memberServices = memberservices;
	}	

	public void setMemberServices(ArrayList<MemberService> memberServices) {
		this.memberServices = memberServices;
	}	
	
	public Member getMember() {
		Members members=new Members();
		Member member=members.search(getNumber());
		return member;
	}
}
