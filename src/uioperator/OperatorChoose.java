package uioperator;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Member;
import object.Provider;
import object.Service;
import user.Members;
import user.Providers;
import user.Services;

/**
 * Servlet implementation class OperatorChoose
 */
@WebServlet("/OperatorChoose")
public class OperatorChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatorChoose() {
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
		ArrayList<String> providerNumberList=new ArrayList<String>();
		Providers providers=new Providers();
		ArrayList<Provider> providerList=providers.getAllProvider();
		for(int i=0;i<providerList.size();i++){
			providerNumberList.add(providerList.get(i).getNumber());
		}
		
		ArrayList<String> memberNumberList=new ArrayList<String>();
		Members members=new Members();
		ArrayList<Member> memberList= members.getAllMember();
		for(int i=0;i< memberList.size();i++){
			memberNumberList.add(memberList.get(i).getNumber());
		}
		
		ArrayList<String> serviceCodeList=new ArrayList<String>();
		Services services=new Services();
		ArrayList<Service> serviceList=services.getAll();
		for(int i=0;i< serviceList.size();i++){
			serviceCodeList.add(serviceList.get(i).getCode());
		}
		
		String choose=request.getParameter("option");
		switch (choose){
		case "option1":
			request.getSession().setAttribute("providerNumberList",providerNumberList);
			request.getRequestDispatcher("ProviderCreate.jsp").forward(request,response);
			break;
		case "option2":
			request.getSession().setAttribute("providerList", providerList);
			request.getRequestDispatcher("ProviderDelete.jsp").forward(request,response);
			break;
		case "option3":
			request.getSession().setAttribute("providerList", providerList);
			request.getRequestDispatcher("ProviderChange.jsp").forward(request,response);
			break;
		case "option4":
			request.getSession().setAttribute("providerList", providerList);
			request.getRequestDispatcher("ProviderShow.jsp").forward(request,response);
			break;
		case "option5":
			request.getSession().setAttribute("memberNumberList",memberNumberList);
			request.getRequestDispatcher("MemberCreate.jsp").forward(request,response);
			break;
		case "option6":
			request.getSession().setAttribute("memberList", memberList);
			request.getRequestDispatcher("MemberDelete.jsp").forward(request,response);
			break;
		case "option7":
			request.getSession().setAttribute("memberList", memberList);
			request.getRequestDispatcher("MemberChange.jsp").forward(request,response);
			break;
		case "option8":
			request.getSession().setAttribute("memberList", memberList);
			request.getRequestDispatcher("MemberShow.jsp").forward(request,response);
			break;
		case "option9":
			request.getSession().setAttribute("serviceCodeList",serviceCodeList);
			request.getRequestDispatcher("SerivceCreate.jsp").forward(request,response);
			break;
		case "option10":
			request.getSession().setAttribute("serviceList", serviceList);
			request.getRequestDispatcher("ServiceDelete.jsp").forward(request,response);
			break;
		case "option11":
			request.getSession().setAttribute("serviceList", serviceList);
			request.getRequestDispatcher("ServiceChange.jsp").forward(request,response);
			break;
		case "option12":
			request.getSession().setAttribute("serviceList", serviceList);
			request.getRequestDispatcher("ServiceShow.jsp").forward(request,response);
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
