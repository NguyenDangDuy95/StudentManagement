package helpers;

public class SQLHelper {

    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SERVER_NAME = "DESKTOP-U2T7NV9";
    public static final String DB_NAME = "ManagerAptech";
    public static final String DB_URL = "jdbc:sqlserver://" + SERVER_NAME + "; databaseName=" + DB_NAME;
    public static final String PASS = "123456789";
    public static final String STUDENT_TABLE = "StudentList";
    public static final String STUDENT_INFO_TABLE = "StudentInfomation";
    public static final String EMPLOYEE_TABLE = "EmployeeList";
    public static final String EMPLOYEE_INFO_TABLE = "InfomationEmployee";
    public static final String COURSE_TABLE = "Course";
    public static final String USER = "sa";
    public static final String GetAllUser = "SELECT * FROM LoginAccount;";
  
    
    public static final String GetAllEmployee = "SELECT * FROM ";

    public static final String GetStudentList = "SELECT * FROM "+STUDENT_TABLE;
    public static final String GetEmployeeList = "SELECT * FROM "+EMPLOYEE_TABLE;
    public static final String GetCourseList = "SELECT * FROM "+ COURSE_TABLE;
    public static String getBatchListByCourseID(String id){
        return String.format("SELECT * FROM BatchList WHERE CourseID = \'%s\'", id);
    }
    
    public static String getSubjectListByCourseID(String id){
        return String.format("SELECT * FROM SubjectList WHERE CourseID = \'%s\'", id);
    }
    
    
    
    public static String getStudentInfoByID(String id){
        return "SELECT * FROM " + STUDENT_INFO_TABLE + " WHERE StudentID = \'"+id+"\';";
    }
    
    
    public static String createSelectSQL(String tableName, String item) {
        return String.format("SELECT %s FROM %s;", item, tableName);
    }
    
    public static String GetStudentByID(String id){
        return "SELECT StudentList.StudentID , FirstName ,LastName , Gender , Birthday , IDCard , Addresss, PlaceOfBirth, PhoneNumber, Email," +
            "Fathername, MotherName, FatherJob, MotherJob, ParentPhone, scholarship, CourseID, StartDate, EndDate" +
            "FROM " + STUDENT_TABLE +
            "LEFT JOIN "+ STUDENT_INFO_TABLE +"ON StudentList.StudentID = StudentInformation.StudentID" +
            "WHERE StudentList.StudentID = \'"+id+"\';";
    }
    public static String GetEmployeeByID(String id){
        return null;
    }
    public static String getElementByID(String id, String role) {
        if (role.equals("student")) {
            return "SELECT * FROM " + STUDENT_TABLE
                    + " LEFT JOIN " + STUDENT_INFO_TABLE
                    + " ON " + STUDENT_TABLE + ".StudentID = " 
                    + STUDENT_INFO_TABLE + ".StudentID WHERE StudentID = \'" + id + "\'";
        } else if (role.equals("employee")) {
            return String.format(" SELECT * "
            + "FROM EmployeeList "
            + "LEFT JOIN InformationEmployee ON InformationEmployee.EmployeeID = EmployeeList.EmployeeID "
            + "LEFT JOIN Position ON Position.PositionID = EmployeeList.PositonID "
            + "WHERE EmployeeList.EmployeeID = '%s' AND Position.PositionName = '%s';", id,"employee");
        } else {
            return String.format(" SELECT * "
            + "FROM EmployeeList "
            + "LEFT JOIN InformationEmployee ON InformationEmployee.EmployeeID = EmployeeList.EmployeeID "
            + "LEFT JOIN Position ON Position.PositionID = EmployeeList.PositonID "
            + "WHERE EmployeeList.EmployeeID = '%s' AND Position.PositionName = '%s';", id,"admin");
        }

    }
}
