package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import bean.dashboardBean;
import util.DBConnection;
import java.util.Calendar;

public class dashboardDAO {

	 private final Connection con;
	 public dashboardDAO()  throws ClassNotFoundException, SQLException {
	        con = DBConnection.getInstance().getConnection();   
	    }
		
		  public List<dashboardBean> getDashboardDetails(int user_id){
			  Calendar now = Calendar.getInstance();
		      List<dashboardBean> libraryList =new ArrayList<dashboardBean>();
		      Statement stmt;
		      dashboardBean b;
		      try {
		    	 System.out.println("aayushi hii"+user_id);
		        int ysale=0;
		        int msale=0;
		        int dsale=0;
		        b = new dashboardBean();
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("SELECT * FROM `rent` as ra,`library_book_mapping` as b,`book` as ba where b.branch_id="+user_id+" and ba.book_id=b.book_id and ra.library_book_mapping_id=b.library_book_mapping_id");
		         
		          
		          int y=now.get(Calendar.YEAR);
		         int m=now.get(Calendar.MONTH)+1;
		         int d=now.get(Calendar.DATE);
		         int cnt=0;
		         int new_arrival=0;
		          while ( rs.next() ) 
		          {
		              
		              String s1=rs.getString("issue_date");
		              String s2=rs.getString("add_on");
		              int rent=rs.getInt("mrp");
		              cnt+=rs.getInt("count");
		              System.out.println("Hii"+y+" "+m+" "+s1.substring(6,10)+" "+s1.substring(3,5));
		              if(Integer.parseInt(s1.substring(6,10))==y) {
		            	  ysale+=rent;
		            	  System.out.println("Hii 1");
		              }
		              if(Integer.parseInt(s1.substring(3,5))==m && Integer.parseInt(s1.substring(6,10))==y) {
		            	  msale+=rent;
		            	  System.out.println("Hii 2");
		              }
		              if(Integer.parseInt(s2.substring(3,5))==m && Integer.parseInt(s2.substring(6,10))==y) {
		            	  new_arrival++;
		            	  
		              }
		              if(Integer.parseInt(s2.substring(0,2))==d ) {
		            	  
		            	  dsale+=rent;
		              }
		          
		              libraryList.add( b );
		          }   
		          b.setYearly_sale(ysale);
		          b.setMonthly_sale(msale);
		          b.setTotal_book(cnt);
		          b.setNew_arrival(new_arrival);
		          b.setWeekly_sale(dsale);
		    	  
		      }  catch(SQLException e) {
		          System.out.println("dao"+e);
		      }
		      return libraryList;
	   }


}
