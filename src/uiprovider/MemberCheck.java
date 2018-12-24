package uiprovider;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Member;
import object.Service;
import user.Members;
import user.Services;

/**
 * Servlet implementation class MemberCheck
 */
@WebServlet("/MemberCheck")
public class MemberCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCheck() {
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
		Object oproviderNumber=request.getSession().getAttribute("providerNumber");
		String providerNumber="";
		if(oproviderNumber!=null){
			providerNumber=oproviderNumber.toString();
		}

		
		String memberNumber=request.getParameter("memberNumber");
		
		Members members=new Members();
		ArrayList<Member> memberList=members.getAllMember();
		Services services=new Services();
		ArrayList<Service> serviceList=services.getAll();
		
		request.getSession().setAttribute("providerNumber", providerNumber);
		for(int i=0;i<memberList.size();i++){
		    Member imember=memberList.get(i);
		    String imemberNumber=imember.getNumber();
		    if(memberNumber.equals(imemberNumber)){
				if(imember.getStatus().equals("A")){
					request.getSession().setAttribute("serviceList", serviceList);
					request.getSession().setAttribute("memberNumber", memberNumber);
					request.getRequestDispatcher("CreateClaim.jsp").forward(request, response);
					return;
				}
				else{
					String error="Suspended!";
					request.getSession().setAttribute("error",error );
					request.getRequestDispatcher("MemberCheck.jsp").forward(request, response);
					return;
				}
		    }
		}
		String error="Not Found!";
		request.getSession().setAttribute("error",error );
		request.getRequestDispatcher("MemberCheck.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
