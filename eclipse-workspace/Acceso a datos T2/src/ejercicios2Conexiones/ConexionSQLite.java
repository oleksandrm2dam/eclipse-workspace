package ejercicios2Conexiones;

import java.sql.*;

public class ConexionSQLite {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement query = null;
		ResultSet result = null;
		String dbPath = "C:/Users/madrid/Documents/GitHub/eclipse-workspace/eclipse-workspace/Acceso a datos T2/databases/";
		String dbName = "prueba.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection(
					"jdbc:sqlite:" + dbPath + dbName
			);
			
			query = connection.createStatement();
			
			result = query.executeQuery(
					"SELECT * FROM departamentos");
			
			while(result.next()) {
				System.out.println(
						result.getInt("dept_no") + " " +
						result.getString("dnombre") + " " + 
						result.getString("loc")
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				query.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
