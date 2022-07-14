package application;

import java.io.IOException;
//import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")//,urlPatterns={"login.go"}, loadOnStartup=1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        String uid = new String(req.getParameter("uid"));
        String pwd = new String(req.getParameter("pwd"));
        String anon = "Anonymous";
        
        // If user does not give name, use anonymous user
        if(uid.isBlank()) {
        	req.getSession().setAttribute("UID", anon);
        }
        
        // you can add your own password handle here... this is only the simple one
        String newPwd = "123";
        if(newPwd.matches(pwd)==true) {
            String contextPath = req.getContextPath();
	        resp.sendRedirect(contextPath + "/chat.jsp");
	    }
    else{
    // Handle wrong password here
    	}
    }
	
	/** Performs some validation and if everything is ok sends user to a chat room page.
	*/    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
        String uid = new String(req.getParameter("uid"));
        String pwd = new String(req.getParameter("pwd"));
        String anon = "Anonymous";
        
        // If user does not give name, use anonymous user
        if(uid.isBlank()) {
        	req.getSession().setAttribute("UID", anon);
        }
        
        // you can add your own password handle here... this is only the simple one
        String newPwd = "123";
        if(newPwd.matches(pwd)==true) {
        	String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/chat.jsp");
	    }
    else{
    // Handle wrong password here
    	}
        
	}	
}