package uiprovider;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProviderChoose
 */
@WebServlet("/ProviderChoose")
public class ProviderChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderChoose() {
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
		String value=request.getParameter("option");
		Object oproviderNumber=request.getSession().getAttribute("providerNumber");
		String providerNumebr="";
		if(oproviderNumber!=null){
			providerNumebr=oproviderNumber.toString();
		}
		request.getSession().setAttribute("providerNumebr", providerNumebr);
		if(value.equals("option1")){
			request.getRequestDispatcher("MemberCheck.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("ProviderDirectory").forward(request, response);
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
