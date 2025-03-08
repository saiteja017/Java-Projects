package employeemanagement;


import java.io.IOException;
import java.io.PrintWriter;
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

public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_4_hibernate?user=root&password=root");
			ps = con.prepareStatement("select * from customerdetails where customer_Email_id =? and password=?");
			ps.setString(1, req.getParameter("email"));
			ps.setString(2, req.getParameter("password"));
			rs = ps.executeQuery();
//			System.out.println("helo come");
			
			if(!rs.next()) {
				ps = con.prepareStatement("select max(customer_id) from customerdetails");
				rs = ps.executeQuery();
				rs.next();
				int records = rs.getInt(1)+1;
				System.out.println("records : "+records);
				ps = con.prepareStatement("insert into customerdetails values(?,?,?,?,?,?,?)");
				ps.setInt(1, records);
				ps.setString(2, req.getParameter("email"));
				ps.setString(3, req.getParameter("name"));
				ps.setString(4, req.getParameter("gender"));
				ps.setString(5, req.getParameter("mobile"));
				ps.setString(6, req.getParameter("password"));
				ps.setString(7, req.getParameter("age"));
				ps.execute();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Not working for chaining PrintWriter
//				PrintWriter pw = resp.getWriter();
//				pw.println("<h1>not there</h1>");
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		rd.forward(req, resp);
	}
}
