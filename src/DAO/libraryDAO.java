package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.library_addressBean;
import bean.library_joinBean;
import bean.libraryBean;
import util.DBConnection;

public class libraryDAO {
	
	 private final Connection con;
		
		public libraryDAO()  throws ClassNotFoundException, SQLException {
			//System.out.println("hii shilpi in database");
	        con = DBConnection.getInstance().getConnection();   
	    }
		  public List<library_joinBean> getLibrary(int library_address_id){
		      List<library_joinBean> libraryList =new ArrayList<library_joinBean>();
		      Statement stmt;
		      library_joinBean b;
		      try {
		        
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("SELECT * FROM `library_address` as la, `library` as l WHERE la.library_id=l.library_id and la.library_status=1 and la.library_address_id="+library_address_id);
		         
		          while ( rs.next() ) 
		          {
		              b = new library_joinBean();
		              b.setLibrary_id(rs.getInt("library_id"));
		              b.setLibrary_address_id(rs.getInt("library_address_id"));
		              b.setLibrary_name(rs.getString("library_name"));
		              b.setBranch_name(rs.getString("branch_name"));
		              b.setBranch_address(rs.getString("branch_address"));
		              b.setContact_no(rs.getString("contact_no"));
		              b.setPincode(rs.getInt("pincode"));
		              b.setEmail(rs.getString("email"));
		              b.setTotal_bill(rs.getInt("total_bill"));
		              b.setLatitude(rs.getDouble("latitude"));
		              b.setLibrary_status(rs.getInt("library_status"));
		              System.out.println("dao"+rs.getString("library_name"));
		              libraryList.add( b );
		          }    
		    	  
		      }  catch(SQLException e) {
		          System.out.println("dao"+e);
		      }
		      return libraryList;
	   }
		  
		  public Boolean updateLibraryAddress(String library_name,int library_address_id,String branch_name,String branch_address,String contact_no,int pincode,String email) {
				int n=0;
				
				try {
				
					Statement stmt2 = con.createStatement();        
			          ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `library_address` as la, `library` as l WHERE la.library_id=l.library_id and la.library_status=1 and la.library_address_id="+library_address_id);
			         int id=0;
			          while ( rs2.next() ) 
			          {
			              id=rs2.getInt("library_id");
			              
			              
			          }
					
					String query="UPDATE  `library` SET library_name='"+library_name+"' where library_id="+id;
					
					Statement stmt = con.createStatement();
					n = stmt.executeUpdate( query );
				
					String query1="UPDATE library_address SET branch_name='"+branch_name+"',branch_address='"+branch_address+"',contact_no='"+contact_no+"',pincode="+pincode+",email='"+email+"' WHERE library_address_id="+library_address_id;
					System.out.println(query1);
						
					Statement stmt1 = con.createStatement();
					n = stmt1.executeUpdate( query1 );
					
				}
				 catch(SQLException e) {
					
			            System.out.println(e.getMessage());
			        }
				return n>0;
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
