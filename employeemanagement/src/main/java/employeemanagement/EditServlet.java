package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("Edit :" +id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_4_hibernate?user=root&password=root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customerdetails where customer_id="+id);
			rs.next();
			PrintWriter pw = resp.getWriter();
			pw.println("<html><body style=\"display: flex; height:100vh; flex-direction: column; gap:20px; align-items: center; background: linear-gradient(90deg, #7bc393, #31b7c2); justify-content: center;\">");

			pw.println("<form method='post' style=\"border: 2px solid #fff; font-size: 20px; display:flex; flex-direction: column; gap:20px; padding: 20px 30px; border-radius: 9px;\">");
			pw.print("<div> <input type='text' name='id' value='"+rs.getInt(1)+"' style='display:none;'></div>");
			pw.print("<div> <label>ID : </label><input type='text'  value='"+rs.getInt(1)+"' disabled ></div>");
			pw.print("<div> <label>NAME : </label><input type='text' name='name' value='"+rs.getString(3)+"' ></div>");
			pw.print("<div> <label>EMAIL : </label><input type='text'  value='"+rs.getString(2)+"' disabled></div>");
			pw.print("<div> <label>PASSWORD : </label><input type='text' name='password' value='"+rs.getString(6)+"' ></div>");
			pw.print("<div> <label>GENDER : </label><input type='text' name='gender' value='"+rs.getString(4)+"' ></div>");
			pw.print("<div> <label>MOBILE : </label><input type='text' name='mobile' value='"+rs.getString(5)+"' ></div>");
			pw.print("<div> <label>AGE : </label><input type='text' name='age' value='"+rs.getInt(7)+"' ></div>");

			pw.println("<div style='text-align: center;'>");
			pw.println("<button style=\"text-decoration: none; border: 2px solid black; padding: 3px 20px;\" formaction='update'>Update Details</button>");
			pw.println("</div>");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
