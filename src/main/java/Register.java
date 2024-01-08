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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request .getParameter("email");
		boolean status = false;
		
		if(!name.isEmpty() && !password.isEmpty()&& !email.isEmpty()) {
			System.out.println("working");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pythondb","root","root");
			PreparedStatement ps1 = con.prepareStatement("Select *from  auth where email=?");
			ps1.setString(1, email);
			ResultSet rs = ps1.executeQuery();          
			status=rs.next();
			System.out.println(name);
			if(status==false) {
			PreparedStatement ps = con.prepareStatement("insert into auth values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			int i = ps.executeUpdate();
			System.out.println(password+email);
			if(i>0) {
//				HttpSession session = request.getSession();
//				session.setAttribute("email",email);
				Cookie ck = new Cookie("email", email);
				response.addCookie(ck);
//				out.print("welcome "+email.substring(0,email.length()-10));
//				RequestDispatcher rd = request.getRequestDispatcher("Home");
//				rd.include(request, response);
				response.sendRedirect("Home");
			
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("register.html");
				rd.include(request, response);
				out.print(" <h3 style=\"color:red\">something wrong try again...... </h3>");
			}
		    }
		    else {
		    	RequestDispatcher rd = request.getRequestDispatcher("register.html");
				rd.include(request, response);
				out.print(" <h3 style=\"color:red\">email already exist...... </h3>");
		    }
		}
		catch(Exception e) {
			System.out.println(e);
		}
		out.close();
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("register.html");
			rd.include(request, response);
			out.print(" <h3 style=\"color:red\">Please fill all column like email,password,name...... </h3>");
		}
	}
    
}
