package uimanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Member;
import object.Provider;
import report.SummaryReport;
import reportreader.SummaryReportReader;
import reportwriter.SummaryReportWriter;
import user.Members;
import user.Providers;

/**
 * Servlet implementation class ManagerChoose
 */
@WebServlet("/ManagerChoose")
public class ManagerChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerChoose() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option=request.getParameter("option");
		switch(option){
		case "option1":
			Providers providers=new Providers();
			ArrayList<Provider> providerList=new ArrayList<Provider>();
			providerList=providers.getAllProvider();
			ArrayList<String> providerNumberList=new ArrayList<String>();
			for(Provider provider:providerList){
				providerNumberList.add(provider.getNumber());
			}
			request.getSession().setAttribute("providerNumberList", providerNumberList);
			request.getRequestDispatcher("jsp/ProviderReportCreate.jsp").forward(request, response);;
			break;
		case "option2":
			ArrayList<String> memberNumberList=new ArrayList<String>();
			Members members=new Members();
			ArrayList<Member> memberList= members.getAllMember();
			for(int i=0;i< memberList.size();i++){
				memberNumberList.add(memberList.get(i).getNumber());
			}
			request.getSession().setAttribute("memberNumberList",memberNumberList);
			request.getRequestDispatcher("jsp/MemberReportCreate.jsp").forward(request,response);
			break;
		case "option3":
			Date reportDate=new Date();
			SummaryReport summaryReport=new SummaryReport(reportDate); 
			SummaryReportWriter summaryReportWriter=new SummaryReportWriter(summaryReport);
			summaryReportWriter.createReportFile();
			
			SummaryReportReader summaryReportReader=new SummaryReportReader(reportDate);
			SummaryReport newSummaryReport=summaryReportReader.getSummaryReport();
			request.getSession().setAttribute("summaryReport",newSummaryReport);
			request.getRequestDispatcher("jsp/SummaryReportShow.jsp").forward(request,response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
