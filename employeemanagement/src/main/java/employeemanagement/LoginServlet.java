package employeemanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con ;
		PreparedStatement ps;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_4_hibernate?user=root&password=root");
			ps = con.prepareStatement("select * from customerdetails where customer_Email_id =? and password=?");
			ps.setString(1, req.getParameter("email"));
			ps.setString(2, req.getParameter("password"));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("login : "+rs.getInt(1));
				req.setAttribute("id", rs.getInt(1));
				RequestDispatcher rd = req.getRequestDispatcher("details");//path(Servlet Path)
				rd.forward(req, resp);
			}else {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, resp);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
