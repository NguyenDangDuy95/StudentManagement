package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseConnection {
	
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName(SQLHelper.JDBC_DRIVER);
			System.out.println("Connecting to database...");
		    con = DriverManager.getConnection(SQLHelper.DB_URL,SQLHelper.USER,SQLHelper.PASS);
		    System.out.println("Creating statement...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return con;
	}
	
	public static void closeConnection(Connection con)
	{
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	

	public static ResultSet getExecutedResultSet(String sqlLine) {
		Connection con = getConnection();
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sqlLine);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
