package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
    private static final long serialVersionUID = 1L;

	public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("loginUsername");
		String password = request.getParameter("loginPassword");
				
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
			PrintWriter out = response.getWriter();
		    String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, username);
		    ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) // login capable
            {
                response.sendRedirect("index.html?username=" + username);		// send us to the login page now :)
            }
            else
            {
            	// No matching user found
            	response.sendRedirect("login.html?error=UserNotFound"); // Redirect to login.html
            }
		
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
