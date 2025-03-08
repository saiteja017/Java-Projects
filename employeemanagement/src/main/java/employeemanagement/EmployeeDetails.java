package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeDetails extends HttpServlet {
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int id = (int)req.getAttribute("id");
			System.out.println("deails"+id);
			
			
			req.setAttribute("id", id);	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_4_hibernate?user=root&password=root");
				PreparedStatement ps = con.prepareStatement("select * from customerdetails where customer_id="+id);
				ResultSet rs = ps.executeQuery();
				rs.next();
				
				PrintWriter pw = resp.getWriter();
				pw.println("<html><body style=\"display: flex; height:100vh; flex-direction: column; gap:20px; align-items: center; background: linear-gradient(90deg, #7bc393, #31b7c2); justify-content: center;\">");
	
				pw.println("<form method='post' style=\"border: 2px solid #fff; font-size: 20px; padding: 20px 30px; border-radius: 9px;\">");
				pw.println("<table ><tr><td>Details of employee</td></tr>");
				pw.print("<div> <input type='text' name='id' value='"+rs.getInt(1)+"' style='display:none;'></div>");
				pw.print("<div> <tr><td> <label>ID : </label></td><td><input type='text' value='"+rs.getInt(1)+"' disabled ></td></div>");
				pw.print("<div> <tr><td> <label>NAME : </label></td><td><input type='text' value='"+rs.getString(3)+"' disabled> </td></div>");
				pw.print("<div> <tr><td> <label>EMAIL : </label></td><td><input type='text' value='"+rs.getString(2)+"' disabled></td></div>");
				pw.print("<div> <tr><td> <label>PASSWORD : </label></td><td><input type='text' value='"+rs.getString(6)+"' disabled></td></div>");
				pw.print("<div> <tr><td> <label>GENDER : </label></td><td><input type='text' value='"+rs.getString(4)+"' disabled></td></div>");
				pw.print("<div> <tr><td> <label>MOBILE : </label></td><td><input type='text' value='"+rs.getString(5)+"' disabled></td></div>");
				pw.print("<div> <tr><td> <label>AGE : </label></td><td><input type='text' value='"+rs.getInt(7)+"' disabled></td></div>");
				pw.println("</table>");
	
				pw.println("<div style='text-align: center;'>");
				pw.println("<button style=\"text-decoration: none; border: 2px solid black; padding: 3px 20px;\" formaction='edit'>Edit Details</button>");
				pw.println("<button id='press' style=\"text-decoration: none; border: 2px solid black; padding: 2px 20px;\" formaction='deleted'>Delete Account</button>");
				pw.println("</div>");
	
				pw.println("</form>");
				pw.println("<script>"
						+"let x = document.getElementById('press');"
						+ "x.addEventListener('click',(e)=>{"
						+ "let confirmDel = confirm('Confirm delete your Accoutn');"
						+ "if(confirmDel) "
						+ " fetch('http://localhost:8080/employeemanagement/deleted?id="+rs.getInt(1)+"' , {method: 'post'});"
						+ "else e.preventDefault();"
						+ "console.log('cliked');"
						+ "})"
						+ "</script>");
				pw.println("</body></html>");
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
