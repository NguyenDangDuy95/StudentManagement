package helpers;

public class SQLHelper {

    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SERVER_NAME = "TMT\\SQLEXPRESS:1433";
    public static final String DB_NAME = "ManagerAptech";
    public static final String DB_URL = "jdbc:sqlserver://" + SERVER_NAME + "; databaseName=" + DB_NAME;
    public static final String USER = "sa";
    public static final String PASS = "123";
//---------------------------------------------------STUDENT---------------------------------------------------------------
    public static final String STUDENT_TABLE = "StudentList";
    public static final String STUDENT_INFO_TABLE = "InformationStudent";
    public static final String COURSE_TABLE = "Course";
    public static final String SUBJECT_LIST_TABLE = "SubjectList";
    public static final String SCORE_TABLE = "Score";
    public static final String ATTDANCE_TABLE = "Attendance"; 
    public static final String BATCH_LIST_TABLE ="BatchList";
//---------------------------------------------------EMPLOYEE--------------------------------------------------------------
    public static final String EMPLOYEE_TABLE = "EmployeeList";
    public static final String EMPLOYEE_INFO_TABLE = "InfomationEmployee";
    public static final String SUBJECT_TEACHER_TABLE = "SubjectTeacher";
    public static final String POSITION_TABLE = "Position";
    public static final String SALARY_TABLE = "Salary";
    
//---------------------------------------------------LOGIN--------------------------------------------------------------
    public static final String GetAllUser = "SELECT * FROM LoginAccount;";
    
    public static String createSelectSQL(String tableName, String item) {
        return String.format("SELECT %s FROM %s;", item, tableName);
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

 //---------------------------------------------------STUDENT--------------------------------------------------------------- 
    
    public static final String GetStudentList = "SELECT * FROM "+STUDENT_TABLE;
 
    public static String getStudentInfoByID(String id){
        return "SELECT * FROM " + STUDENT_INFO_TABLE + " WHERE StudentID = \'"+id+"\';";
    }
    
 // CourseID : ACCP i15 , ACCP i17 
    public static String GetSubjectByCourseID(String courseID){
        return "SELECT CourseID, SubjectID, SubjectName, Hours ,Semester" +
                "FROM"+ SUBJECT_LIST_TABLE +
                "WHERE CourseID = \'"+courseID+"\';";
    }   

    public static String GetStudentByID(String id){
        return "SELECT StudentList.StudentID , FirstName ,LastName , Gender , Birthday , IDCard , Addresss, PlaceOfBirth, PhoneNumber, Email," +
            "Fathername, MotherName, FatherJob, MotherJob, ParentPhone, scholarship, CourseID, StartDate, EndDate" +
            "FROM " + STUDENT_TABLE +
            "LEFT JOIN "+ STUDENT_INFO_TABLE +"ON StudentList.StudentID = StudentInformation.StudentID" +
            "WHERE StudentList.StudentID = \'"+id+"\';";
    }
    // hiển thị thông tin người dạy - môn đã và đang dạy :
    public static final String GetscheduleByBatchID(String id){
        return "SELECT DISTINCT FirstName, LastName, SubjectTeacher.CourseID, BatchID, Semester, SubjectName, SubjectTeacher.StartDate, SubjectTeacher.EndDate" +
            "FROM "+ SUBJECT_TEACHER_TABLE +
            "LEFT JOIN "+ EMPLOYEE_INFO_TABLE +" ON "+ SUBJECT_TEACHER_TABLE +".EmployeeID = "+ EMPLOYEE_INFO_TABLE +".EmployeeID" +
            "LEFT JOIN "+ SUBJECT_LIST_TABLE +" ON "+ SUBJECT_TEACHER_TABLE +".SubjectID = "+ SUBJECT_LIST_TABLE +".SubjectID" +
            "WHERE BatchID = \'"+id+"\'" +
            "ORDER BY "+SUBJECT_TEACHER_TABLE+".StartDate ASC;";
    }
    
 //---------------------------------------------------EMPLOYEE--------------------------------------------------------------  
    
    public static final String GetEmployeeList = "SELECT * FROM "+EMPLOYEE_TABLE;


    
    public static String GetEmployeeByID(String id){
        return null;
    }
    
    

}
