package helpers;

public class SQLHelper {
	public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SERVER_NAME = "DESKTOP-U2T7NV9";
	public static final String DB_NAME = "SCHOOLMANAGEMENT";
	public static final String DB_URL = "jdbc:sqlserver://"+SERVER_NAME+"; databaseName="+ DB_NAME;
	public static final String PASS = "123456789";
	public static final String STUDENT_TABLE = "STUDENT";
	public static final String USER = "sa";
	public static final String getAllUser = "SELECT LoginID , Pass FROM LoginAccount;";
        public static final String getEmployeeByID = " SELECT InformationEmployee.* \n" +
                                                       "FROM EmployeeList\n" +
                                                       "LEFT JOIN InformationEmployee ON InformationEmployee.EmployeeID = EmployeeList.EmployeeID\n" +
                                                       "LEFT JOIN Position ON Position.PositionID = EmployeeList.PositonID\n" +
                                                       "WHERE EmployeeList.EmployeeID = '%s' AND Position.PositionName = '%s';";
	public static String createSelectSQL(String tableName,String item)
	{
		return String.format("SELECT %s FROM %s;", item, tableName);
	}
        public static String createGetEmployeeByID(String id, String role)
        {
            return String.format(id, role);
        }
}
