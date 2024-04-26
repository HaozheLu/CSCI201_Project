package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
       
    private static final long serialVersionUID = 1L;

	public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

        // Enter your own username/password here (or configure mySQL Workbench to not require it)
		String SQLusername = "";        
		String SQLpassword = "";

		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    // Class.forName() may only be needed with old JVMs, like JVM 14
		    // In that case, also remove throws ClassNotFoundException
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    
		    String myString = "jdbc:mysql://localhost/CSCI201_Project?user=root";
		    conn = DriverManager.getConnection(myString);
		    st = conn.createStatement();
		    
		    // Check if the username already exists in the Users table
		    String sql_exists = "SELECT COUNT(*) FROM Users WHERE username = ?";
		    ps = conn.prepareStatement(sql_exists);
		    ps.setString(1, username); // Set the username to check
		    rs = ps.executeQuery();
		    
		    if (rs.next()) {
		        int count = rs.getInt(1);
		        if (count == 0) {
		            // If username does not exist, insert it into Users
		            String sql_insert = "INSERT INTO Users (uname, pword, email, fname, lname) VALUES (?, ?, ?, ?, ?)";
		            ps = conn.prepareStatement(sql_insert);
		            ps.setString(1, username); // Set the username to insert
		            ps.setString(2, password);
		            ps.setString(3, email);
		            ps.setString(4, firstname);
		            ps.setString(5, lastname);		            
		            ps.executeUpdate();
		            System.out.println("Username inserted successfully.");
	                response.sendRedirect("login_success.html?username=" + username);		
		        } else {
		            System.out.println("Username already exists.");
	                response.sendRedirect("login.html?errorName=userExists");
		        }
		    }
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (rs != null) rs.close();
		        if (ps != null) ps.close();
		        if (st != null) st.close();
		        if (conn != null) conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}
}
