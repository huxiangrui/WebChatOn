package uimanager;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Provider;
import report.ProviderReport;
import reportreader.ProviderReportReader;
import reportwriter.ProviderReportWriter;
import user.Providers;

/**
 * Servlet implementation class ProviderReportCreate
 */
@WebServlet("/ProviderReportCreate")
public class ProviderReportCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderReportCreate() {
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
		String providerNumber=request.getParameter("radio");
		Providers providers=new Providers();
		Provider provider=providers.search(providerNumber);
		
		Date reportDate=new Date();
		ProviderReport providerReport=new ProviderReport(provider, reportDate);
		
		ProviderReportWriter providerReportWriter=new ProviderReportWriter(providerReport);
		providerReportWriter.createReportFile();
		
		ProviderReportReader providerReportReader=new ProviderReportReader(provider, reportDate);
		ProviderReport newProviderReport=providerReportReader.getProviderReport();
		
		request.getSession().setAttribute("providerReport",newProviderReport);
		request.getRequestDispatcher("jsp/ProviderReportShow.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
