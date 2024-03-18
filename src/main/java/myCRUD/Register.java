package myCRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		PrintWriter out = resp.getWriter();
	    String name = req.getParameter("name1");
	    String email = req.getParameter("email1");
	    String pass = req.getParameter("pass1");
	    String gender = req.getParameter("gender1");
	    String city = req.getParameter("city1");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletcrud","root","2022");
		PreparedStatement ps = connection.prepareStatement("insert into register values(?,?,?,?,?)");
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, pass);
		ps.setString(4, gender);
		ps.setString(5, city);
		int status = ps.executeUpdate();
		
		if(status>0) {
			resp.setContentType("text/html");
			out.print("<h3 style = 'color : green'>User registered successfully</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
			
		}
		else {
			resp.setContentType("text/html");
			out.print("<h3 style = 'color : red'>Registration failed due to some error </h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
	} catch (Exception e) {
		resp.setContentType("text/html");
		out.print("<h3 style = 'color : red'>Exception occured"+e.getMessage()+"</h3>");
		RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
		rd.include(req, resp);
	}
	
	}

}
