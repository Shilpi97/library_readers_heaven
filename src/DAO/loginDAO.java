package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.library_addressBean;
import util.DBConnection;

public class loginDAO {
	
	 private final Connection con;
		
		public loginDAO()  throws ClassNotFoundException, SQLException {
			//System.out.println("hii shilpi in database");
	        con = DBConnection.getInstance().getConnection();   
	    }
		
		public library_addressBean checkLogin(String email,String password) {
			//System.out.println("hii shilpi in dao");
			Statement stmt;
			library_addressBean user=null;
			int n=0;
			 try {
			        String query="select * from library_address where email= '"+email+"'and password='"+password+"';";
			        System.out.println(query);
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery(query);
		          while(rs.next()) {
		        	n++;
		        	user=new library_addressBean();
		        	//System.out.println(rs.getString("name"));
		        	user.setLibrary_address_id(rs.getInt("library_address_id"));
		          }
		          if(n==1)
		          {
		        	  return user;
		          }
		          
		          else {
		        	  return null;
		          }
		    	  
		      }  catch(SQLException e) {
		          System.out.println("dao"+e);
		      }
			return null;
		}
		
}
