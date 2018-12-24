package uiprovider;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Service;
import user.Services;

/**
 * Servlet implementation class ProviderDirectory
 */
@WebServlet("/ProviderDirectory")
public class ProviderDirectory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderDirectory() {
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
		request.getSession().setAttribute("providerNumber", providerNumber);
		
		Services services=new Services();
		ArrayList<Service> serviceList=services.getAll();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String outprint="<!DOCTYPE html>\n"+
				  "<html>\n" +
			      "<head><title>ProviderDirectory</title></head>\n"+
				  "<body>\n"+
			      "<table border='1'>\n"+
			      "<tr>\n"+
			      "<th>服务代码</th>\n"+
			      "<th>服务名称</th>\n"+
			      "<th>服务费用</th>\n"+
			      "</tr>\n";
		
		for(int i=0;i<serviceList.size();i++){
			String code=serviceList.get(i).getCode();
			String name=serviceList.get(i).getName();
			double fee=serviceList.get(i).getFee();
			outprint+= "<tr>\n"+
				      "<th>"+code+"</th>\n"+
				      "<th>"+name+"</th>\n"+
				      "<th>"+fee+"</th>\n"+
				      "</tr>\n";
		}
		outprint+="</table>"+
				"<form action='ProviderChoose.jsp' method='get'>"+
				" <input type='submit' value='确认'/>"+
				"</form>"+
				"</body>";
		
		out.print(outprint);
		out.flush(); 
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
