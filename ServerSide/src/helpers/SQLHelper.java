package helpers;

public class SQLHelper {
	public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SERVER_NAME = "DESKTOP-U2T7NV9";
	public static final String DB_NAME = "SCHOOLMANAGEMENT";
	public static final String DB_URL = "jdbc:sqlserver://"+SERVER_NAME+"; databaseName="+ DB_NAME;
	public static final String PASS = "123456789";
	public static final String STUDENT_TABLE = "STUDENT";
	public static final String USER = "sa";
	public static final String getAllUser = "";
	public static String createSelectSQL(String tableName,String item)
	{
		return String.format("SELECT %s FROM %s;", item, tableName);
	}
        public static String getElementByID(String id, String role)
        {
            //return String.format(role, args);
            return null;
        }
}
