package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.library_addressBean;
import bean.libraryBean;
import util.DBConnection;

public class libraryDAO {
	
	 private final Connection con;
		
		public libraryDAO()  throws ClassNotFoundException, SQLException {
			//System.out.println("hii shilpi in database");
	        con = DBConnection.getInstance().getConnection();   
	    }
		
		public int checkPassword(int id,String password){
		      //List<adminBean> adminList =new ArrayList<adminBean>();
		      Statement stmt;
		      int flag=0;
		     // adminBean b;
		      try {
		        
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("select * from library_address where library_id= " + id + " AND password='"+ password+ "'; ");
		         System.out.println(rs);
		          while ( rs.next() ) 
		          {
		              flag=1;
		             
		          }    
		    	  
		      }  catch(SQLException e) {
		          System.out.println("dao"+e);
		      }
		      
		      return flag;
		   }
		 public int addLibrary(String library_name,String branch_name,String branch_address,String email,String password,int pincode,String mobile_no) {
				int n=0;
				int id=0;
				int id1=0;
				try {
					System.out.println(library_name);
					String query="Select library_id from library Where library_name='"+library_name+"' ";
				
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery( query );
					System.out.println("rs is sss:"+query);
					while(rs.next()) {
					id=rs.getInt("library_id");
					//System.out.println("Id:::" +id);
					}
					
					System.out.println("Id:::" +id);
					
					if(id==0)
					{
						
						String qry="Insert into library values(null,'"+library_name+"')";
						Statement stmt1 = con.createStatement();
						n = stmt1.executeUpdate( qry );
						System.out.println("value of N: "+n);
						String select="select library_id from library order by library_id desc limit 1";
						System.out.println("Select : "+select);
						ResultSet rs1 = stmt.executeQuery( select );
						while(rs1.next())
						 id1=rs1.getInt("library_id");
						System.out.println("ID1 :" + id1);
						
						String Qry="Insert into library_address values(null,'"+branch_name+"',"+id1+",'"+branch_address+"',"+mobile_no+","+pincode+",0,0,0,'"+email+"','"+password+"',0) ";
						Statement Stmt = con.createStatement();
						int x = Stmt.executeUpdate( Qry );
						System.out.println("value of x: "+x);
						
						
						
						
					}
					else
					{
						
						String Query="Insert into library_address values(null,'"+branch_name+"',"+id+",'"+branch_address+"',"+mobile_no+","+pincode+",0,0,0,'"+email+"','"+password+"',0) ";
						Statement Stmt1 = con.createStatement();
						int x = Stmt1.executeUpdate( Query );
						System.out.println("value of x: "+x);
						
					}
					
				}
					//n = stmt.executeQuery( query );
					
					
				 catch(SQLException e) {
			            System.out.println(e.getMessage());
			        }
				return id;
				
				
			}
		
}
