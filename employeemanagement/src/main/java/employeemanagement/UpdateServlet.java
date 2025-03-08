package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println(req.getParameter("id"));
//		System.out.println(req.getParameter("name"));
//		System.out.println(req.getParameter("name"));
//		System.out.println(req.getParameter("name"));

		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_4_hibernate?user=root&password=root");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("update  customerdetails set Custmer_name='"+  req.getParameter("name") +"', Gender ='"+  req.getParameter("gender")
					+ "', Mobile_Number='"
					+  req.getParameter("mobile")
					+ "', password ='"
					+  req.getParameter("password")
					+ "', age ="
					+ Integer.parseInt( req.getParameter("age")) + " where customer_id="+id);
			
			req.setAttribute("id", id);
			RequestDispatcher rd = req.getRequestDispatcher("details");
			rd.include(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
