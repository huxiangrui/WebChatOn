package uioperator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Member;
import user.Members;

/**
 * Servlet implementation class MemberDelete
 */
@WebServlet("/MemberDelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDelete() {
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
		Members members=new Members();
		String[] numberList=request.getParameterValues("checkbox");
		if( numberList!=null&& numberList.length>0) {
			for(int i= 0 ;i< numberList.length;i++){
				String number=numberList[i];
				Member member=members.search(number);
				members.remove(member);
			}  
		}
		
		request.getRequestDispatcher("OperatorChoose.jsp").forward(request, response);
	}

}
