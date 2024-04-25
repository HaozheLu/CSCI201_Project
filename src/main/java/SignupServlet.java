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
		
		String username = request.getParameter("signupUsername");
		String password = request.getParameter("signupPassword");
		String confirmPassword = request.getParameter("signupConfirmPassword");
		String email = request.getParameter("signupEmail");

        if (!confirmPassword.equals(password))
        {
            response.sendRedirect("login.html?error=PasswordMismatch");		// send us to the login page now :)
            return;
        }
        // Enter your own username/password here (or configure mySQL Workbench to not require it)
        String SQLusername = "";        
        String SQLpassword = "";
        
        Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Class.forName() may only bee needed with old JVMs, like JVM 14
			// In that case, also remove throws ClassNotFoundException
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String myString = "jdbc:mysql://localhost/CSCI201_Project?user=root";
			conn = DriverManager.getConnection(myString);
			st = conn.createStatement();
			String sql = "INSERT INTO Users (email, username, password) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            System.out.println("User created " + ps.executeUpdate());
            response.sendRedirect("login.html?username=" + username);		// send us to the login page now :)
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally { }
	}

}
