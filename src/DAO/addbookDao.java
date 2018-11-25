package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import bean.addbookBean;
import util.DBConnection;


public class addbookDao {
	 private final Connection con;	
		public addbookDao()  throws ClassNotFoundException, SQLException {
			//System.out.println("hii shilpi in database");
	        con = DBConnection.getInstance().getConnection();   
	    }
		
		public List<addbookBean> getAllcategory(){
		      List<addbookBean> category =new ArrayList<addbookBean>();
		      Statement stmt;
		      addbookBean b;
		      try {
		        System.out.println("llllll");
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("select * from category");
		         
		          while ( rs.next() ) 
		          {
		              b = new addbookBean();
		              b.setCategory_id(rs.getInt("category_id"));
		              b.setCategory_name(rs.getString("category_name"));
		              System.out.println("dao"+rs.getString("category_name"));
		              category.add( b );
		          }    
		    	  
		      }  catch(SQLException e) {
		          System.out.println("dao"+e);
		      }
		      System.out.println("ccccc");
		      return category;
		}
		

		public int addbook(String book_title,String author,int edition,String publisher,String isbn,int pages,int mrp,int categorys,String description,int publish_year,String book_language,String strDate,String modified_on,int status,int user_id,int count) {
			int n=0;
			int n1=0;
			int id=0;
			try {
				String query="insert into book values(null,'"+book_title+"','"+author+"',"+edition+",'"+publisher+"','"+isbn+"',"+pages+","+mrp+",'img/product/9.jpg',"+categorys+",'"+description+"',"+publish_year+",'"+book_language+"',0,'"+strDate+"','"+
						modified_on+"');";
			
				Statement stmt = con.createStatement();
				n = stmt.executeUpdate( query );
				
				String select="select book_id from book order by book_id desc limit 1";
				ResultSet rs = stmt.executeQuery( select );
				while(rs.next()) {
				id=rs.getInt("book_id");
				
				String query1="insert into library_book_mapping values(null,"+user_id+","+id+","+count+");";
				
				Statement stmt1 = con.createStatement();
				n1 = stmt1.executeUpdate( query1 );
				if(n1>0) {
					System.out.println("successfully inserted");
				}
				else
				{
					System.out.println("no no no");
				}
				}
			
				
				
				
				
				
			}
			 catch(SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
			return n;
			
		}
		
		
		public List<addbookBean>getallbook(int user_id)
		
		{
			List<addbookBean> bookList =new ArrayList<addbookBean>();
		      Statement stmt;
		      Statement stmt1;
		      addbookBean b;
		      try {
		        stmt1=con.createStatement();
		        ResultSet rs1=stmt1.executeQuery("select book_id,count from library_book_mapping where branch_id= " + user_id + " ");
		        while(rs1.next()) {
		        	int bookid=rs1.getInt("book_id");
		        	int count=rs1.getInt("count");
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("select book.book_id,book.book_title,book.author,book.edition,book.publisher,book.isbn,book.pages,book.mrp,book.photo,book.description,book.publish_year,book.book_language,category.category_id,category.category_name from book,category Where book.category_id=category.category_id and book.book_id= " + bookid + " and book.status=0");
		          
		          while(rs.next())
		          {
		        	  b= new addbookBean();
		        	  b.setBook_id(rs.getInt("book_id"));
		        	  b.setBook_title(rs.getString("book_title"));
		        	  b.setAuthor(rs.getString("author"));
		        	  b.setEdition(rs.getInt("edition"));
		        	  b.setPublisher(rs.getString("publisher"));
		        	  b.setIsbn(rs.getString("isbn"));
		        	  b.setPages(rs.getInt("pages"));
		        	  b.setMrp(rs.getInt("mrp"));
		        	  b.setPhoto(rs.getString("photo"));
		        	  b.setCategory_name(rs.getString("category_name"));
		              b.setDescription(rs.getString("description"));
		              b.setPublish_year(rs.getInt("publish_year"));
		              b.setBook_language(rs.getString("book_language"));
		              b.setCount(count);
		              bookList.add(b);
		          }
		          }}
		      catch(Exception e) {
		    	  System.out.println("dao"+e);
		      		}
		      return bookList;
		         
		          
		      	}
		
		
public List<addbookBean>getBookById(int id,int user_id)
		
		{
			List<addbookBean> bookList =new ArrayList<addbookBean>();
		      Statement stmt;
		      Statement stmt1;
		      addbookBean b;
		      try {
		        stmt1=con.createStatement();
		        ResultSet rs1=stmt1.executeQuery("select count from library_book_mapping where branch_id= " + user_id + " and book_id= " + id + " ");
		        while(rs1.next())
		        {int count=rs1.getInt("count");
		    	  stmt = con.createStatement();        
		          ResultSet rs = stmt.executeQuery("select book.book_id, book.book_title,book.author,book.edition,book.publisher,book.isbn,book.pages,book.mrp,book.photo,book.description,book.publish_year,book.book_language,category.category_id,category.category_name from book,category Where book.category_id=category.category_id and book.book_id=" + id + " ");
		          
		          while(rs.next())
		          {
		        	  b= new addbookBean();
		        	  b.setBook_id(rs.getInt("book_id"));
		        	  b.setBook_title(rs.getString("book_title"));
		        	  b.setAuthor(rs.getString("author"));
		        	  b.setEdition(rs.getInt("edition"));
		        	  b.setPublisher(rs.getString("publisher"));
		        	  b.setIsbn(rs.getString("isbn"));
		        	  b.setPages(rs.getInt("pages"));
		        	  b.setMrp(rs.getInt("mrp"));
		        	  b.setPhoto(rs.getString("photo"));
		        	  b.setCategory_name(rs.getString("category_name"));
		              b.setDescription(rs.getString("description"));
		              b.setPublish_year(rs.getInt("publish_year"));
		              b.setBook_language(rs.getString("book_language"));
		              b.setCount(count);
		              bookList.add(b);
		          }
		        } }
		      catch(Exception e) {
		    	  System.out.println("dao"+e);
		      		}
		      return bookList;
		         
		          
		      	}
		int i=1;
public Boolean deleteBook(int id,int user_id) {
	
	String sql = "delete from library_book_mapping WHERE book_id = " + id + " and branch_id= "+user_id+"; ";
    int n=0;
    try {
    	System.out.println("In try2");
        Statement stmt = con.createStatement();
        n = stmt.executeUpdate( sql );
    }
    catch(SQLException e) {
        System.out.println(e.getMessage());
    }
    return n>0;
}

public int updatebook(String book_title,String author,int edition,String publisher,String isbn,int pages,int mrp,int categorys,String description,int publish_year,String book_language,String modified_on,int id,int user_id,int count) {
	int n=0;
	System.out.println("in update"+categorys+id);
	//int id=0;
	try {
		String query="Update book set book_title='" + book_title + "' , author='" + author+"', edition="+edition+", publisher='"+publisher+"', isbn='"+isbn+"',pages="+pages+",mrp= "+mrp+", category_id= "+categorys+",description= '"+description+"',publish_year="+publish_year+",book_language='"+book_language+"',modified_on='"+modified_on+"' where book_id= " + id + "";
		//String query="update admin set email= '" + email + "' , password='" + password + "' where admin_id = " + id + " ; "; 
	System.out.println(query);
		Statement stmt = con.createStatement();
		n = stmt.executeUpdate( query );
		
		String query1="Update library_book_mapping set count=" + count + " where book_id= " + id + " and branch_id= "+user_id+ "";
		//String query="update admin set email= '" + email + "' , password='" + password + "' where admin_id = " + id + " ; "; 
	System.out.println(query);
		Statement stmt1 = con.createStatement();
		int n1 = stmt1.executeUpdate( query1 );
		
		
		
	}
	 catch(SQLException e) {
            System.out.println(e.getMessage());
        }
	return n;
	
	
}
//book_title,modified_on,id,user_id,count		      
public int updatebookquantity(String book_title,String modified_on,int id,int user_id,int count) {
	int n=0;
	
	//int id=0;
	try {
		
		
		String query1="Update library_book_mapping set count=" + count + " where book_id= " + id + " and branch_id= "+user_id+ "";
		//String query="update admin set email= '" + email + "' , password='" + password + "' where admin_id = " + id + " ; "; 
	//System.out.println(query);
		Statement stmt1 = con.createStatement();
		n = stmt1.executeUpdate( query1 );
		
		
		
	}
	 catch(SQLException e) {
            System.out.println(e.getMessage());
        }
	return n;
	
	
}
			
	
}


