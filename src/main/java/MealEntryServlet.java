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

public class MealEntryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		String mealName = request.getParameter("mealName");
		int caloriesConsumed = Integer.parseInt(request.getParameter("caloriesConsumed"));
		
		JsonObject servletresponse = enterMeal(userid, mealName, caloriesConsumed);
		
		response.setContentType("application/Json");
		response.setCharacterEncoding("WTF-8");
		response.getWriter().write(servletresponse.toString());
	}
	
	private JsonObject enterMeal(int userid, String mealName, int caloriesConsumed)
	{
		JsonObject resultJson = new JsonObject();
		
		String SQLUsername = "";
		String SQLPassword = "";
		
		String JDBC = "jdbc:mysql://localhost/CSCI201_Project?user=" + SQLUsername + "&password=" + SQLPassword;
		
		try(Connection conn = DriverManager.getConnection(JDBC))
		{
			if(caloriesConsumed < 0)
			{
				resultJson.addProperty("entry", false);
				resultJson.addProperty("message", "Negative cal values");
				
				return resultJson;
			}
			
			String query = "INSERT INTO Meals (idUsers, mealName, consumedCals) Values (?, ?, ?)";
			
			try(PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setInt(1, userid);
				ps.setString(2, mealName);
				ps.setInt(3, caloriesConsumed);
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