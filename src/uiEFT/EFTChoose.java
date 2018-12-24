package uiEFT;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.EFT;
import user.EFTs;

/**
 * Servlet implementation class EFTChoose
 */
@WebServlet("/EFTChoose")
public class EFTChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EFTChoose() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String choose=request.getParameter("option");
		EFTs efts=new EFTs();
		switch(choose){
		case "option1":
			EFT EFTReport=new EFT();
			efts.add(EFTReport);
			request.getSession().setAttribute("EFT", EFTReport);
			request.getRequestDispatcher("jsp/EFTShow.jsp").forward(request, response);
			break;
		case "option2":
			ArrayList<EFT> eftList=efts.findAll();
			ArrayList<String> reportDateList=new ArrayList<String>();
			for(EFT eft:eftList){
				reportDateList.add(eft.getReportDateString());
			}
			request.getSession().setAttribute("reportDateList", reportDateList);
			request.getRequestDispatcher("jsp/EFTSearch.jsp").forward(request, response);
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
