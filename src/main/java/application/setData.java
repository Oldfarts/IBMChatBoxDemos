package application;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

/**
 * Servlet implementation class setData
 */
@WebServlet("setData")
public class setData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setData() {
        super();
        // TODO Auto-generated constructor stub
    }
    //private Messages list = Messages.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Following chapter is commented since otherwise it will add hardcoded lines to DB2 */
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>;";
		//String user = "snh52364";
		
		//PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		Date date = new Date();
		long ltime = date.getTime();
		Timestamp finaltime = new Timestamp(ltime);
		String finalStringTime = finaltime.toString();

		
	    String uid = request.getParameter("uid");  
	    String msg = request.getParameter("msg"); 
		try {
			  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				System.out.println("Crashed at trying to find the driver.");
				e.printStackTrace(); 
			}
			try {
				conn = (Connection) DriverManager.getConnection(myAppUrl);
			} catch (SQLException el) {
				System.out.println ("Error msg: " + el.getMessage());
				System.out.println ("SQLSTATE: " + el.getSQLState());
				System.out.println ("Error code: " + el.getErrorCode());
				el.printStackTrace();
			}

			try {			

				stmt = conn.prepareStatement("INSERT INTO SNH52364.testtable (TIME, UID, MSG) values (?,?,?)");
				stmt.setString(1, finalStringTime);
				stmt.setString(2, uid);
				stmt.setString(3, msg);
				
				try {
				int rs = stmt.executeUpdate();
	        	stmt.close();
	        	conn.close();
				if(rs==1) {
		        	// Forward page back to chat.jsp page to reload it
		        	request.getRequestDispatcher("chat.jsp").forward(request, response);
					}
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block

			}
			
        	// Forward page back to chat.jsp page to reload it
        	request.getRequestDispatcher("chat.jsp").forward(request, response);
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Forward page back to chat.jsp page to reload it
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>;";
		//String user = "snh52364";
		
		//PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		Date date = new Date();
		long ltime = date.getTime();
		Timestamp finaltime = new Timestamp(ltime);
		String finalStringTime = finaltime.toString();

		
	    String uid = request.getParameter("uid");  
	    String msg = request.getParameter("msg"); 
		try {
			  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				System.out.println("Crashed at trying to find the driver.");
				e.printStackTrace(); 
			}
			try {
				conn = (Connection) DriverManager.getConnection(myAppUrl);
			} catch (SQLException el) {
				System.out.println ("Error msg: " + el.getMessage());
				System.out.println ("SQLSTATE: " + el.getSQLState());
				System.out.println ("Error code: " + el.getErrorCode());
				el.printStackTrace();
			}

			try {			

				stmt = conn.prepareStatement("INSERT INTO SNH52364.testtable (TIME, UID, MSG) values (?,?,?)");
				stmt.setString(1, finalStringTime);
				stmt.setString(2, uid);
				stmt.setString(3, msg);
				
				try {
				int rs = stmt.executeUpdate();
	        	stmt.close();
	        	conn.close();
				if(rs==1) {
		        	// Forward page back to chat.jsp page to reload it
		        	request.getRequestDispatcher("chat.jsp").forward(request, response);
					}		
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	// Forward page back to chat.jsp page to reload it
        	request.getRequestDispatcher("chat.jsp").forward(request, response);
	}
}
    


