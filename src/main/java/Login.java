

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ANSI_RESET ="\u001B[0m";
	public static final String ANSI_YELLOW="\u001B[33m";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean status=false;
		
		PrintWriter out = response.getWriter();
		if(!email.isEmpty() && !password.isEmpty()) {
			System.out.println(ANSI_YELLOW+"working"+ANSI_RESET);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pythondb","root","root");
            
			PreparedStatement ps = con.prepareStatement("Select *from  auth where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			status=rs.next();
			if(status==true) {
				
				
				Cookie ck= new Cookie("email", email);
				response.addCookie(ck);
//				out.print("welcome "+email.substring(0,email.length()-10));
//				RequestDispatcher rd = request.getRequestDispatcher("Home");
		     	response.sendRedirect("Home");
				System.out.println("login done");
//				rd.include(request, response);
				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
				out.print(" <h3 style=\"color:red\">email,password not matched try again........</h3>");
			}
		    
		}
		catch(Exception e) {
			System.out.println(e);
		}
		out.close();
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			out.print(" <h3 style=\"color:red\">Please fill all column like email,password...... </h3>");
		}
	}
}

