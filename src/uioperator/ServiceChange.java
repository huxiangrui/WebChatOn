package uioperator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Service;
import user.Services;

/**
 * Servlet implementation class ServiceChange
 */
@WebServlet("/ServiceChange")
public class ServiceChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceChange() {
        super();
        // TODO Auto-generated constructor stub
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
		String code=request.getParameter("code");
		String name=request.getParameter("name");
		double fee=Double.valueOf(request.getParameter("fee"));
		
		Services services=new Services();
		Service service=new Service(code, name, fee);
		services.update(service);
		
	    request.getRequestDispatcher("OperatorChoose.jsp").forward(request, response);;
	}

}
