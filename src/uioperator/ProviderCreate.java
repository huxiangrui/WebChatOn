package uioperator;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Provider;
import user.Providers;

/**
 * Servlet implementation class ProviderCreate
 */
@WebServlet("/ProviderCreate")
public class ProviderCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderCreate() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		 String number=request.getParameter("number");
		 String name=request.getParameter("name");
		 String address=request.getParameter("address");
		 String city=request.getParameter("city");
		 String country=request.getParameter("country");
		 String zip=request.getParameter("zip");
		 String email=request.getParameter("email");
		 String type=request.getParameter("type");
		 
		 Providers providers=new Providers();
		 Provider provider=new Provider(number, name, address, city, country, zip, email, type);
		 providers.add(provider);
		 
		 request.getRequestDispatcher("OperatorChoose.jsp").forward(request, response);;
	}

}
