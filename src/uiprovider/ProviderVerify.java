package uiprovider;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Provider;
import user.Providers;

/**
 * Servlet implementation class ProviderVerifyServlet
 */
@WebServlet(description = "ProviderVerify", urlPatterns = { "/ProviderVerify" })
public class ProviderVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderVerify() {
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String providerNumber=request.getParameter("providerNumber");
		
		Providers providers=new Providers();
		ArrayList<Provider> providerList=providers.getAllProvider();
		ArrayList<String> providerNumberList=new ArrayList<String>();
		for(int i=0;i<providerList.size();i++){
			providerNumberList.add(providerList.get(i).getNumber());
		}
		
		if(providerNumberList.contains(providerNumber)){
			request.getSession().setAttribute("providerNumber", providerNumber);
			request.getRequestDispatcher("ProviderChoose.jsp").forward(request, response);
		}
		else{
			String error=providerNumber+" not found!";
			request.getSession().setAttribute("error",error );
			request.getRequestDispatcher("ProviderVerify.jsp").forward(request, response);
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
