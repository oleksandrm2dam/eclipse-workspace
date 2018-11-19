package rurales;

import java.sql.*;
import java.util.ArrayList;

public class SQLite {
	
	private Connection connection;
	private Statement statement;
	
	public SQLite(String dbPath, String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath + dbName);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void roomPriceLessThan(float price) {
		String query = "SELECT * FROM room WHERE price <= " + price;
		
		try {
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				System.out.println(
						result.getString("type") + " " + 
						result.getBoolean("bathroom") + " " + 
						result.getFloat("price")
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
