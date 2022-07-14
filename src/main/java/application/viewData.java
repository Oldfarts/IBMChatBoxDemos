package application;

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewData
 */
@WebServlet("/viewData")
public class viewData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String messages;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getData() {
		String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>;";

	    Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(myAppUrl);
		} catch (SQLException el) {
			// TODO Auto-generated catch block
            System.out.println ("Error msg: " + el.getMessage());
            System.out.println ("SQLSTATE: " + el.getSQLState());
            System.out.println ("Error code: " + el.getErrorCode());
			el.printStackTrace();
		}
	    
	    Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			 while(e!=null) {
            System.out.println ("Error msg: " + e.getMessage());
            System.out.println ("SQLSTATE: " + e.getSQLState());
            System.out.println ("Error code: " + e.getErrorCode());
            e = e.getNextException(); // Check for chained exceptions
          }
		   System.out.println("Problem in"
                    + " loading or registering IBM DB2 JDBC driver");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs = null;
	    

		try {
			  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			    System.out.println("Crashed here");
				e.printStackTrace(); 
			}
		try{
			String sql = "SELECT TIME, UID, MSG FROM SNH52364.TESTTABLE";
			rs = stmt.executeQuery(sql);

            String addStr=null; // = "<table><tr><th>Uid</th><th>Msg</th></tr>";
            while(rs.next()){
            	//addStr = rs.getString(1) + rs.getString(2);
            	//addStr += "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td>";
                messages = messages + addStr;

            }
            //addStr += "</table>";
            //out.println(addStr);
        	rs.close();
        	stmt.close();
        	conn.close();
   	
		}catch(Exception ef){
			System.err.println(ef);
		}
    	return messages;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>;";
        
		PrintWriter out = response.getWriter();
	    Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(myAppUrl);
		} catch (SQLException el) {
			// TODO Auto-generated catch block
            System.out.println ("Error msg: " + el.getMessage());
            System.out.println ("SQLSTATE: " + el.getSQLState());
            System.out.println ("Error code: " + el.getErrorCode());
			el.printStackTrace();
		}
	    
	    Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			 while(e!=null) {
            System.out.println ("Error msg: " + e.getMessage());
            System.out.println ("SQLSTATE: " + e.getSQLState());
            System.out.println ("Error code: " + e.getErrorCode());
            e = e.getNextException(); // Check for chained exceptions
          }
		   System.out.println("Problem in"
                    + " loading or registering IBM DB2 JDBC driver");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs = null;
	    

		try {
			  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			    System.out.println("Crashed here");
				e.printStackTrace(); 
			}
		try{
			String sql = "SELECT UID,MSG FROM SNH52364.TESTTABLE";
			rs = stmt.executeQuery(sql);

            String addStr = "<table><tr><th>Time</th><th>Uid</th><th>Msg</th></tr>";
            while(rs.next()){
            	addStr += "<tr>"+"<td>"+rs.getString(1)+"</td>"+"<td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>";
            }
            addStr += "</table>";
            messages = addStr;
            out.println(addStr);
        	rs.close();
        	stmt.close();
        	conn.close();
        	

        	//request.getRequestDispatcher("chat.jsp").forward(request, response);
        	
		}catch(Exception ef){
			System.err.println(ef);
		}


	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>;";

	// List to hold Student objects 
    //ArrayList<Messages> std = new ArrayList<Messages>(); 

	PrintWriter out = response.getWriter();
    Connection conn = null;
	try {
		conn = (Connection) DriverManager.getConnection(myAppUrl);
	} catch (SQLException el) {
		// TODO Auto-generated catch block
        System.out.println ("Error msg: " + el.getMessage());
        System.out.println ("SQLSTATE: " + el.getSQLState());
        System.out.println ("Error code: " + el.getErrorCode());
		el.printStackTrace();
	}
    
    Statement stmt = null;
	try {
		stmt = conn.createStatement();
	} catch (SQLException e) {
		 while(e!=null) {
        System.out.println ("Error msg: " + e.getMessage());
        System.out.println ("SQLSTATE: " + e.getSQLState());
        System.out.println ("Error code: " + e.getErrorCode());
        e = e.getNextException(); // Check for chained exceptions
      }
	   System.out.println("Problem in"
                + " loading or registering IBM DB2 JDBC driver");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    ResultSet rs = null;
    

	try {
		  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
		    System.out.println("Crashed here");
			e.printStackTrace(); 
		}
	try{
		String sql = "SELECT TIME, UID, MSG FROM SNH52364.TESTTABLE";
		rs = stmt.executeQuery(sql);

        
        String addStr = "<table><tr><th>Time</th><th>Uid</th><th>Msg</th></tr>";
        while(rs.next()){
        	addStr += "<tr>"+"<td>"+rs.getString(1)+"</td>"+"<td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>";
        }
	        addStr += "</table>";
	        messages = addStr;
	        out.println(addStr);
	    	rs.close();
	    	stmt.close();
	    	conn.close();
          }catch(Exception ef){
		System.err.println(ef);
        }
	}
}
