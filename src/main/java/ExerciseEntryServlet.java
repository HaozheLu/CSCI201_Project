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

public class ExerciseEntryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		String workoutName = request.getParameter("workoutName");
		int caloriesBurned = Integer.parseInt(request.getParameter("caloriesBurned"));
		
		JsonObject servletresponse = enterExercise(userid, workoutName, caloriesBurned);
		
		response.setContentType("application/Json");
		response.setCharacterEncoding("WTF-8");
		response.getWriter().write(servletresponse.toString());
	}
	
	private JsonObject enterExercise(int userid, String workoutName, int caloriesBurned)
	{
		JsonObject resultJson = new JsonObject();
		
		String SQLUsername = "";
		String SQLPassword = "";
		
		String JDBC = "jdbc:mysql://localhost/CSCI201_Project?user=" + SQLUsername + "&password=" + SQLPassword;
		
		try(Connection conn = DriverManager.getConnection(JDBC))
		{
			if(caloriesBurned < 0)
			{
				resultJson.addProperty("entry", false);
				resultJson.addProperty("message", "Negative cal values");
				
				return resultJson;
			}
			
			String query = "INSERT INTO Workouts (idUsers, mealName, consumedCals) Values (?, ?, ?)";
			
			try(PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setInt(1, userid);
				ps.setString(2, workoutName);
				ps.setInt(3, caloriesBurned);
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