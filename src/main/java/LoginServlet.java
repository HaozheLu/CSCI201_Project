package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("loginEmail");
        String username = request.getParameter("loginUsername");
        String password = request.getParameter("loginPassword");
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String myString = "jdbc:mysql://localhost/CSCI201_Project?user=root";
            conn = DriverManager.getConnection(myString);
            
            String sql = "SELECT * FROM Users WHERE (username = ? OR email = ?) AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            
            rs = ps.executeQuery();

            if (rs.next()) {
                String loggedInUsername = rs.getString("username"); // Assuming 'username' is the column name in the Users table
                response.sendRedirect("login_success.html?username=" + loggedInUsername);
            } else {
                response.sendRedirect("login.html?error=WrongCredentials");
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("total disaster -- classnotfound or sql exception");
            response.sendRedirect("error.html");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
