package ui;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class UserInterface
 */
@WebServlet("/UserInterface")
public class UserInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInterface() {
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
		String choose=request.getParameter("option");
		switch (choose){
		case "option1":
			request.getRequestDispatcher("jsp/ProviderVerify.jsp").forward(request,response);
			break;
		case "option2":
			request.getRequestDispatcher("jsp/OperatorChoose.jsp").forward(request,response);
			break;
		case "option3":
			request.getRequestDispatcher("jsp/ManagerChoose.jsp").forward(request,response);
			break;
		case "option4":
			request.getRequestDispatcher("jsp/EFTChoose.jsp").forward(request,response);
			break;
		case "option5":
			destroy();
		default:
			destroy();
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
