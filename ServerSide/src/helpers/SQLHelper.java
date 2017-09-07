package helpers;

public class SQLHelper {

    //database info
    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SERVER_NAME = "DESKTOP-G57KTP4";
    public static final String DB_NAME = "ManagerAptech";
    public static final String DB_URL = "jdbc:sqlserver://" + SERVER_NAME + "; databaseName=" + DB_NAME;
    public static final String USER = "sa";
    public static final String PASS = "123456789";

    //Account
    public static String getAccountList() {
        return "SELECT Username, Password, UserID, UserRoles FROM LoginAccount";
    }

    //batch
    /**
     *
     * @param id
     * @return
     */
    public static String getBatchListByCourseID(String id) {
        return String.format("SELECT * FROM BatchList WHERE CourseID = \'%s\'", id);
    }

    /**
     * Get all Batch from Database
     *
     * @return
     */
    public static String getFullBatchList() {
        return "SELECT BatchID,BatchName,CourseID\n"
                + "FROM BatchList";
    }

    //subject
    /**
     *
     * @param id
     * @return a subject list with courseID
     */
    public static String getSubjectListByCourseID(String id) {
        return String.format("SELECT * FROM SubjectList WHERE CourseID = \'%s\'", id);
    }

    //student
    public static String getStudentListByBatchID(String id) {
        return "SELECT StudentList.StudentID ,BatchID, FirstName,LastName,Gender,Birthday,PersonalID,Addresss,BirthPlace,PhoneNumber,Email,FatherName,\n"
                + "MotherName, FatherJob,MotherJob,ParentPhone,scholarship,CourseID,StartDate,EndDate\n"
                + "FROM StudentList\n"
                + "LEFT JOIN InformationStudent ON StudentList.StudentID = InformationStudent.StudentID\n"
                + "WHERE StudentList.BatchID=\'" + id + "\'";
    }

    public static String getFullStudentList() {
        return "SELECT StudentList.StudentID ,BatchID, FirstName,LastName,Gender,Birthday,PersonalID,Addresss,BirthPlace,PhoneNumber,Email,FatherName,\n"
                + "MotherName, FatherJob,MotherJob,ParentPhone,scholarship,CourseID,StartDate,EndDate\n"
                + "FROM StudentList\n"
                + "LEFT JOIN InformationStudent ON StudentList.StudentID = InformationStudent.StudentID;";
    }

    public static String getStudentByID(String id) {
        return "SELECT StudentList.StudentID ,BatchID, FirstName,LastName,Gender,Birthday,PersonalID,Addresss,BirthPlace,PhoneNumber,Email,FatherName,\n"
                + "MotherName, FatherJob,MotherJob,ParentPhone,scholarship,CourseID,StartDate,EndDate\n"
                + "FROM StudentList\n"
                + "LEFT JOIN InformationStudent ON StudentList.StudentID = InformationStudent.StudentID\n"
                + "WHERE StudentList.StudentID = \'" + id + "\';";
    }

    //employee
    public static String getFullInfoEmployeeList() {
        return "SELECT EmployeeList.EmployeeID,FirstName,LastName,PositionName,SalaryValue,Gender,BirthDay,PersonalID,\n"
                + "Email,PhoneNumber,Addresss,BirthPlace,FatherName, MotherName,FatherJob,MotherJob,ParentPhone,StartDate,EndDate\n"
                + "FROM Position,Salary,EmployeeList\n"
                + "LEFT JOIN InformationEmployee\n"
                + "ON EmployeeList.EmployeeID = InformationEmployee.EmployeeID\n"
                + "WHERE Position.PositionID = EmployeeList.PositonID And Salary.SalaryID = EmployeeList.SalaryID";
    }

    public static String getEmployeeByID(String id) {
        return "SELECT EmployeeList.EmployeeID,FirstName,LastName,PositionName,SalaryValue,Gender,BirthDay,PersonalID,\n"
                + "Email,PhoneNumber,Addresss,BirthPlace,FatherName, MotherName,FatherJob,MotherJob,ParentPhone,StartDate,EndDate\n"
                + "FROM Position,Salary,EmployeeList\n"
                + "LEFT JOIN InformationEmployee\n"
                + "ON EmployeeList.EmployeeID = InformationEmployee.EmployeeID\n"
                + "WHERE Position.PositionID = EmployeeList.PositonID \n"
                + "And Salary.SalaryID = EmployeeList.SalaryID\n"
                + "And EmployeeList.EmployeeID = \'"+id+"\'";
    }

    //course
    public static String getCourseList() {
        return "SELECT CourseID,CourseName\n"
                + "FROM Course";
    }
}
