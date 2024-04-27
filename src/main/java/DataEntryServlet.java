import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

public class DataEntryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int userid = Integer.parseInt(request.getParameter("userid"));
		int exerciseCals = Integer.parseInt(request.getParameter("exerciseCals"));		
		int maxCals = Integer.parseInt(request.getParameter("maxCals"));
		String restriction = request.getParameter("restriction");
		
		JsonObject servletresponse = enterData(userid, exerciseCals, maxCals, restriction);
		
		response.setContentType("application/Json");
		response.setCharacterEncoding("WTF-8");
		response.getWriter().write(servletresponse.toString());
	}
	
	private JsonObject enterData(int userid, int exerciseCals, int maxCals, String restriction)
	{
		JsonObject resultJson = new JsonObject();
		
		String SQLUsername = "";
		String SQLPassword = "";
		
		String JDBC = "jdbc:mysql://localhost/CSCI201_Project?user=" + SQLUsername + "&password=" + SQLPassword;
		
		try(Connection conn = DriverManager.getConnection(JDBC))
		{
			if(exerciseCals < 0 || maxCals < 0)
			{
				resultJson.addProperty("entry", false);
				resultJson.addProperty("message", "Negative cal values");
				
				return resultJson;
			}
			
			String query = "INSERT INTO Preferences (idUsers, dietary, maxCals, exerciseCals) Values (?, ?, ?, ?)";
			
			try(PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setInt(1, userid);
				ps.setString(2, restriction);
				ps.setInt(3, maxCals);
				ps.setInt(4, exerciseCals);
				ps.executeUpdate();
				
				resultJson.addProperty("entry", true);
				
				return resultJson;
			}
		}
		catch(SQLException e)
		{
			JsonObject error = new JsonObject();
			
			error.addProperty("entry", false);
			error.addProperty("message", "Error");
			
			return error;
		}
	}
}