package uiprovider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Claim;
import user.Claims;

/**
 * Servlet implementation class AddClaim
 */
@WebServlet("/AddClaim")
public class AddClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String DATE_FORMAT = "MM-dd-yyyy";
	private static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH-mm-ss";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClaim() {
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

		Object omemberNumber=request.getSession().getAttribute("memberNumber");
		String memberNumber="";
		if(omemberNumber!=null){
			memberNumber=omemberNumber.toString();
		}

		Object oserviceDateString=request.getSession().getAttribute("serviceDateString");
		String serviceDateString="";
		if(oserviceDateString!=null){
			serviceDateString=oserviceDateString.toString();
		}

		Object oserviceCode=request.getSession().getAttribute("serviceCode");
		String serviceCode="";
		if(oserviceCode!=null){
			serviceCode=oserviceCode.toString();
		}

		Object onote=request.getSession().getAttribute("note");
		String note="";
		if(onote!=null){
			note=onote.toString();
		}

		Object ocurrentDateString=request.getSession().getAttribute("currentDateString");
		String currentDateString="";
		if(ocurrentDateString!=null){
			currentDateString=ocurrentDateString.toString();
		}
		try {
			dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);  
			Date currentDate = dateFormatter.parse(currentDateString);
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			Date serviceDate = dateFormatter.parse(serviceDateString);
			Claim claim=new Claim(currentDate, serviceDate, providerNumber, memberNumber, serviceCode,note);
			Claims claims=new Claims();
			claims.add(claim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("providerNumber", providerNumber);
		request.getRequestDispatcher("ProviderChoose.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
