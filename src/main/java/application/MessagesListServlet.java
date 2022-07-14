package application;

import java.sql.*;

import java.io.IOException;

public class MessagesListServlet {
	//private static final long serialVersionUID = 1L;

    //private ArrayList<Messages> std;
    private String addStr;
    
    
    public MessagesListServlet() {
    }
    
    public String getArraylistValues()throws IOException {
		// TODO Auto-generated method stub
		String myAppUrl = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB:user=snh52364;password=<password here>";

	    Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(myAppUrl);
		} catch (SQLException el) {
			// TODO Auto-generated catch block
			el.printStackTrace();
		}
	    
	    Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			 while(e!=null) {
            e = e.getNextException(); // Check for chained exceptions
          }
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs = null;
	    

		try {
			  // Load the IBM Data Server Driver for JDBC and SQL with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();	  
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace(); 
			}
		try{
			String sql = "SELECT TIME, UID, MSG FROM SNH52364.TESTTABLE";
			rs = stmt.executeQuery(sql);
			
            addStr = "<table><tr><th>Time</th><th>Uid</th><th>Msg</th></tr>";
            while(rs.next()){
            	addStr += "<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>";
            }
            addStr += "</table>";
            //System.out.println(addStr);
        	rs.close();
        	stmt.close();
        	conn.close();
        	
		}catch(Exception ef){
			System.err.println(ef);
		}
		return addStr;
    }
}
