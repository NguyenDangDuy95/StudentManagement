package helpers;

public class SQLHelper {

    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SERVER_NAME = "DESKTOP-U2T7NV9";
    public static final String DB_NAME = "ManagerAptech";
    public static final String DB_URL = "jdbc:sqlserver://" + SERVER_NAME + "; databaseName=" + DB_NAME;
    
    public static final String USER = "sa";
    public static final String PASS = "123456789";
    
    public static final String STUDENT_TABLE = "StudentList";
    public static final String STUDENT_INFO_TABLE = "InformationStudent";
    
    public static final String COURSE_TABLE = "Course";
    
    public static final String SUBJECT_LIST_TABLE = "SubjectList";
    
    public static final String SCORE_TABLE = "Score";
    
    public static final String ATTDANCE_TABLE = "Attendance"; 
    
    public static final String BATCH_LIST_TABLE ="BatchList";
    
    public static final String SUBJECT_TEACHER_TABLE = "SubjectTeacher";
    
    public static final String POSITION_TABLE = "Position";
    
    public static final String SALARY_TABLE = "Salary";
    
    public static final String EMPLOYEE_TABLE = "EmployeeList";
    public static final String EMPLOYEE_INFO_TABLE = "InfomationEmployee";
    

    public static final String GetAllEmployee = "SELECT * FROM ";
    public static final String GetCourseList = "SELECT * FROM "+ COURSE_TABLE;
    public static final String GetAllUser = "SELECT * FROM LoginAccount;";
    public static final String GetStudentList = "SELECT * FROM "+STUDENT_TABLE;
    public static final String GetEmployeeList = "SELECT * FROM "+EMPLOYEE_TABLE;
  
    
    
    
    public static String getBatchListByCourseID(String id){
        return String.format("SELECT * FROM BatchList WHERE CourseID = \'%s\'", id);
    }
    
    public static String getSubjectListByCourseID(String id){
        return String.format("SELECT * FROM SubjectList WHERE CourseID = \'%s\'", id);
    }
    
    public static String getStudentInfoByID(String id){
        return "SELECT * FROM " + STUDENT_INFO_TABLE + " WHERE StudentID = \'"+id+"\';";
    }
    
    public static String GetEmployeeByID(String id){
        return null;
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
}
