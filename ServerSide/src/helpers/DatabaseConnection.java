package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    public static Connection con = null;

    public static void getConnection() {

        try {
            Class.forName(SQLHelper.JDBC_DRIVER);
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection(SQLHelper.DB_URL, SQLHelper.USER, SQLHelper.PASS);
            System.out.println("Creating statement...");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getExecutedResultSet(String sqlLine) {
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

    public static boolean getUpdateResultSet(String sqlLine) {
        int result = 0;
        try {
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(sqlLine);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0) return true;
        return false;
    }
}
