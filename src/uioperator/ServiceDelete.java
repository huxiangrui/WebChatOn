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
 * Servlet implementation class ServiceDelete
 */
@WebServlet("/ServiceDelete")
public class ServiceDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceDelete() {
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
		Services services=new Services();
		String[] numberList=request.getParameterValues("checkbox");
		if( numberList!=null&& numberList.length>0) {
			for(int i= 0 ;i< numberList.length;i++){
				String number=numberList[i];
				Service service=services.find(number);
				services.remove(service);
			}  
		}
		
		request.getRequestDispatcher("OperatorChoose.jsp").forward(request, response);
	}

}
