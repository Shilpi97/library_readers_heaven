package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import javax.servlet.http.HttpSession;
import java.util.List;


import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.addbookDao;
import DAO.loginDAO;
//import DAO.loginDAO;
import bean.addbookBean;
import bean.userBean;

/**
 * Servlet implementation class addbookServlet
 */
public class updatebookquantityservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatebookquantityservlet() {
    //    super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	System.out.println("happy birthday");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	
}
		
	
	//System.out.println("happy birthday");
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println("id in servlet is: "+id);
		String book_title=request.getParameter("book_title");
		//System.out.println(nameofbook);
		
		int count=Integer.parseInt(request.getParameter("count"));
		
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		//String strDate = date.format(d);
		String modified_on=date.format(d);
		
		
		try {
			//System.out.println("category  :"+categorys);
			HttpSession session=request.getSession();	
			Integer user_id=(Integer)session.getAttribute("user_id");
			System.out.println(user_id+"   : user_id");
			int book_id=new addbookDao().updatebookquantity(book_title,modified_on,id,user_id,count);
			System.out.println("book id:"+book_id);
			if(book_id>0)
			{
				System.out.println("Updated");
				response.sendRedirect("./viewbook.jsp");
				}
			else {
				System.out.println("Not udated");
			}
		}
		catch(Exception e){
			System.out.println("hii");
		}
		
				//userBean user=new loginDAO().checkLogin(email, password);
		
		
		
		
		
		
		
		
	}
		
	}

