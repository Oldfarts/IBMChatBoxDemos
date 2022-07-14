package application;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyChatBotDemo
 */
@WebServlet("/MyChatBotDemos")
public class MyChatBotDemos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MyChatBotDemos() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getOutputStream().println("<h1>hello world!</h1>");
		response.setContentType("text/html);charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Add/remove names</title>");
			out.println("</head>");
			out.println("<body>");			
			out.println("<h1>Add/remove names" + request.getContextPath() + "</h1>");
			out.println("</body>");			
			out.println("</html>");			
		}
		//<a href="/index.html"> Main</a>
		//<a href="/viewData">View Data</a>
		//<a href="/setData">Set Data</a>

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
