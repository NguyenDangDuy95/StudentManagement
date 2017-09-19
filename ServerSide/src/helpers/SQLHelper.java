package helpers;

import java.util.Vector;
import models.Attendance;
import models.Batch;
import models.Gender;
import models.Score;
import models.Student;

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

    public static String getBatchScoreList(String id) {
        return "SELECT *\n"
                + "FROM Score\n"
                + "WHERE Batch = \'" + id + "\'";
    }

    public static String getCurrentTeacher(String batchID) {
        return "SELECT EmployeeID\n"
                + "FROM SubjectTeacher\n"
                + "WHERE SubjectTeacher.EndDate IS NULL\n"
                + "AND SubjectTeacher.BatchID = \'" + batchID + "\';";
    }

    public static String getCurrentSubject(String batchID) {
        return "SELECT SubjectID\n"
                + "FROM SubjectTeacher\n"
                + "WHERE EndDate IS NULL\n"
                + "AND SubjectTeacher.BatchID = \'" + batchID + "\';";
    }

    public static String getCurrentAttendance(String batchID, String subjectID) {
        return "SELECT * \n"
                + "FROM Attendance\n"
                + "WHERE BatchID = \'" + batchID + "\'\n"
                + "AND SubjectID = \'" + subjectID + "\'";
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

    public static String getSubjectByID(String id) {
        return String.format("SELECT * FROM SubjectList WHERE SubjectID = \'%s\'", id);
    }

    public static String getSubjectListByBatchID(String id) {
        return "SELECT *\n"
                + "FROM SubjectTeacher\n"
                + "WHERE SubjectTeacher.BatchID =\'" + id + "\'";
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

    public static String DeleteStudent(String id) {
        return "DELETE FROM InformationStudent WHERE StudentID = \'" + id + "\'\n"
                + "DELETE FROM Score WHERE StudentID = \'" + id + "\'\n"
                + "DELETE FROM Attendance WHERE StudentID = \'" + id + "\'\n"
                + "DELETE FROM StudentList WHERE StudentID = \'" + id + "\'";
    }
    
    public static String DeleteEmployee(String id){
        return "DELETE FROM InformationEmployee WHERE EmployeeID = \'"+id+"\'\n"
                + "DELETE FROM EmployeeList WHERE EmployeeID = \'"+id+"\'";
    }

    public static String GetUserPassOfStudent(Student std) {
        return "SELECT Username, Password \n"
                + "FROM LoginAccount,StudentList\n"
                + "WHERE LoginAccount.UserID = StudentList.StudentID \n"
                + "AND PersonalID = \'"+std.getPersonalID()+"\' \n"
                + "AND BatchID = \'"+std.getBatch()+"\'";
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
                + "And EmployeeList.EmployeeID = \'" + id + "\'";
    }

    //course
    public static String getCourseList() {
        return "SELECT *\n"
                + "FROM Course";
    }

    //update attendance
    public static String insertAttendance(Attendance att) {
        String status;
        if (att.getStatus().equals("X") || att.getStatus().equals("x")) {
            status = "1";
        } else if (att.getStatus().equals("V") || att.getStatus().equals("v")) {
            status = "0";
        } else {
            status = "NULL";
        }
        return "INSERT INTO Attendance VALUES (\'" + att.getStudentID() + "\',\'" + att.getSubjectID() + "\',\'" + att.getCourseID() + "\',\'" + att.getBatchID() + "\'," + status + ") ";
    }

    public static String deleteAttendance(String studentId, String subjectId) {
        return "DELETE FROM Attendance WHERE StudentID=\'" + studentId + "\' AND SubjectID=\'" + subjectId + "\'";
    }

    public static String deleteScore(Score sc) {
        return "DELETE FROM Score WHERE StudentID = \'" + sc.getStudentID() + "\' AND SubjectID= \'" + sc.getSubjectID() + "\'";
    }
}
