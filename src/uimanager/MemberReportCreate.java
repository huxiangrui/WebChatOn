package uimanager;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Member;
import report.MemberReport;
import reportreader.MemberReportReader;
import reportwriter.MemberReportWriter;
import user.Members;

/**
 * Servlet implementation class MemberReportCreate
 */
@WebServlet("/MemberReportCreate")
public class MemberReportCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberReportCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memberNumber=request.getParameter("radio");
		Members members=new Members();
		Member member=members.search(memberNumber);
		
		Date reportDate=new Date();
		MemberReport memberReport=new MemberReport(member, reportDate);
		
		MemberReportWriter memberReportWriter=new MemberReportWriter(memberReport);
		memberReportWriter.createReportFile();
		
		MemberReportReader memberReportReader=new MemberReportReader(member, reportDate);
		MemberReport newMemberReport=memberReportReader.getMemberReport();
		
		request.getSession().setAttribute("memberReport",newMemberReport);
		request.getRequestDispatcher("jsp/MemberReportShow.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
