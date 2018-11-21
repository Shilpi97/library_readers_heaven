package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.libraryDAO;
import DAO.loginDAO;
import bean.library_addressBean;

/**
 * Servlet implementation class loginServlet
 */
public class addlibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addlibraryServlet() {
    //    super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	System.out.println("happy birthday");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//System.out.println("happy birthday");
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("hii shilpi");
		String library_name=request.getParameter("library_name");
		String branch_name=request.getParameter("branch_name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String cpassword=request.getParameter("cpassword");
		String branch_address=request.getParameter("branch_address");
		int pincode=Integer.parseInt(request.getParameter("pincode"));
		String mobile_no=request.getParameter("mobile_no");
		try {
			if (password.equals(cpassword)) {
				System.out.println("in try1");
				int library_id = new libraryDAO().addLibrary(library_name,branch_name,branch_address,email,password,pincode,mobile_no);
				//response.sendRedirect("./dashboard.jsp");
				}
			 else {
				//response.sendRedirect("addlibrary.jsp");
				//System.out.println("Password Not Match");
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Login Servlet "+e);
		}
	}


}
