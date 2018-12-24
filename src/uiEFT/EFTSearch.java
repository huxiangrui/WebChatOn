package uiEFT;

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

import object.EFT;
import user.EFTs;

/**
 * Servlet implementation class EFTSearch
 */
@WebServlet("/EFTSearch")
public class EFTSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "MM-dd-yyyy";	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EFTSearch() {
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
		try {
			String reportDateString=request.getParameter("radio");
			dateFormatter = new SimpleDateFormat(DATE_FORMAT); 
			Date reportDate = dateFormatter.parse(reportDateString);
			EFTs efts=new EFTs();;
			EFT eft=efts.find(reportDate);
			request.getSession().setAttribute("EFT", eft);
			request.getRequestDispatcher("EFTShow.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
