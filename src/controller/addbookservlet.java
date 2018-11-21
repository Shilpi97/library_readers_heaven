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
public class addbookservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbookservlet() {
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
		List<addbookBean> category =null;
		try {
			category= new addbookDao().getAllcategory();
			request.setAttribute("category", category);
		request.getRequestDispatcher("addbook.jsp").forward(request, response);
	
	
}
	catch(Exception e) {
		System.out.println("in Catch");
	}
	
}
		
	
	//System.out.println("happy birthday");
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String book_title=request.getParameter("book_title");
		//System.out.println(nameofbook);
		String author=request.getParameter("author");
		int edition=Integer.parseInt(request.getParameter("edition"));
		String publisher=request.getParameter("publisher");
		String isbn=request.getParameter("isbn");
		int pages=Integer.parseInt(request.getParameter("pages"));
		int mrp=Integer.parseInt(request.getParameter("mrp"));
		int count=Integer.parseInt(request.getParameter("count"));
		String description=request.getParameter("description");
		int publish_year = Integer.parseInt(request.getParameter("publish_year"));
		String book_language=request.getParameter("book_language");
		int categorys=Integer.parseInt(request.getParameter("categorys"));
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = date.format(d);
		String modified_on=date.format(d);
		
		try {
			System.out.println("category  :"+categorys);
			HttpSession session=request.getSession();	
			Integer user_id=(Integer)session.getAttribute("user_id");
			System.out.println(user_id+"   : user_id");
			int book_id=new addbookDao().addbook(book_title,author,edition,publisher,isbn,pages,mrp,categorys,description,publish_year,book_language,strDate,modified_on,0,user_id,count);
			System.out.println("book id:"+book_id);
			if(book_id>0)
			{
				response.sendRedirect("./viewbook.jsp");
			}
			else {
				response.sendRedirect("./addbook.jsp");
				
			}
			
		}
		catch(Exception e){
			System.out.println("hii");
		}
		
				//userBean user=new loginDAO().checkLogin(email, password);
		
		
		
		
		
		
		
		
	}

}
