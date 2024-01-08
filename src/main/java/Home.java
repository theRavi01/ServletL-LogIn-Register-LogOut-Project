

import java.io.IOException;
import java.io.PrintWriter;

import auth.excepion.UserException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out =response.getWriter();

//	    HttpSession session = request.getSession(false);
	    Cookie ck[]= request.getCookies();
	    System.out.println(ck[0].getValue());
	    if(ck!=null) {
	    String email =ck[0].getValue();
	    if(!email.equals("") || email!=null) {
	    out.print("<header>\r\n"
	    		+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" >\r\n"
	    		+ "</header>\r\n"
	    		+ "<div class=\"wrapper\">\r\n"
	    		+ "        <div class=\"logo\">\r\n"
	    		+ "            <img src=\"img/st1.png\" alt=\"\">\r\n"
	    		+ "        </div>\r\n"
	    		+ "        <div class=\"text-center mt-4 name\">\r\n"
	    		+ "          Hey <h3 style=\"color:red\">"+email.substring(0,email.length()-10)+"</h3> Welcome to CodeWithRavikant\r\n"
	    		+ "          <h5>*************************</h5>\r\n"
	    		+ "          <h5> \"auth module using these languages..\"</h5>\r\n"
	    		+ "         <h3>HTML</h3>\r\n"
	    		+ "         <h3>CSS</h3>\r\n"
	    		+ "         <h3>Servlet</h3>\r\n"
	    		+ "         <h3>JDBC</h3>\r\n"
	    		+ "         <h3>MySQL DB</h3>\r\n"
	    		+ "         <a href=\"Logout\">\r\n"
	    		+ "         <button class=\"btn mt-3\" >LogOut</button>\r\n"
	    		+ "         </a>\r\n"
	    		+ "        </div>\r\n"
	    		+ "    </div>");
	    }
	    }
	    else {
	    	RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			out.print(" <h3 style=\"color:red\">Please Login first...... </h3>");
		}
	    
	}

}
