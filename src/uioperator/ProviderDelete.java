package uioperator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Provider;
import user.Providers;

/**
 * Servlet implementation class ProviderDelete
 */
@WebServlet("/ProviderDelete")
public class ProviderDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderDelete() {
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
		Providers providers=new Providers();
		String[] numberList=request.getParameterValues("checkbox");
		if( numberList!=null&& numberList.length>0) {
			for(int i= 0 ;i< numberList.length;i++){
				String number=numberList[i];
				Provider provider=providers.search(number);
				providers.remove(provider);
			}  
		}
		
		request.getRequestDispatcher("OperatorChoose.jsp").forward(request, response);
	}

}
