

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    request.getRequestDispatcher("Login.html").include(request, response);
//    HttpSession session = request.getSession();
//    session.invalidate();
    System.out.println("working");
    Cookie ck=new Cookie("email","");
    ck.setMaxAge(0);
    response.addCookie(ck);
    out.print("<p style='color:red' successfully logged out");
    out.close();
	}

}
