/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import static helpers.DatabaseConnection.con;
import helpers.MyConstants;
import helpers.SQLHelper;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import models.Gender;
import models.Student;

/**
 *
 * @author Duy
 */
public class StudentDataService {

    public static Vector<Student> GetStudentListByBatchID(String batchID) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getStudentListByBatchID(batchID));
        return createStudentListFromResultSet(rs);
    }

    public static Student GetStudentByID(String id) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getStudentByID(id));
        if (rs.next() == false) {
        } else {

            Student std = new Student(
                    rs.getString("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("CourseID"),
                    rs.getString("BatchID"),
                    MyConstants.getGender(rs.getString("Gender")),
                    rs.getDate("BirthDay"),
                    rs.getString("Addresss"),
                    rs.getString("BirthPlace"),
                    rs.getString("PersonalID"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email"),
                    rs.getString("FatherName"),
                    rs.getString("FatherJob"),
                    rs.getString("MotherName"),
                    rs.getString("MotherJob"),
                    rs.getString("ParentPhone"),
                    rs.getString("Scholarship"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate")
            );

            return std;

        }
        return null;
    }

    public static Vector<Student> GetStudentList() throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getFullStudentList());
        return createStudentListFromResultSet(rs);
    }

    private static Vector<Student> createStudentListFromResultSet(ResultSet rs) throws SQLException {
        Vector<Student> stdList = new Vector<>();
        while (rs.next()) {
            Student std = new Student(
                    rs.getString("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("CourseID"),
                    rs.getString("BatchID"),
                    MyConstants.getGender(rs.getString("Gender")),
                    rs.getDate("BirthDay"),
                    rs.getString("Addresss"),
                    rs.getString("BirthPlace"),
                    rs.getString("PersonalID"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email"),
                    rs.getString("FatherName"),
                    rs.getString("FatherJob"),
                    rs.getString("MotherName"),
                    rs.getString("MotherJob"),
                    rs.getString("ParentPhone"),
                    rs.getString("Scholarship"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate")
            );
            stdList.addElement(std);
        }
        return stdList;
    }    
    public static void AddStudent(Student std) throws SQLException{
        CallableStatement csmt = DatabaseConnection.con.prepareCall("{call addStudent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        csmt.setString(1, std.getFirstName());
        csmt.setString(2, std.getLastName());
        csmt.setString(3, std.getCourse());
        csmt.setString(4, std.getBatch());
        csmt.setString(5, ((std.getGender()==Gender.Male)?"Male":"Female"));
        csmt.setDate(6, java.sql.Date.valueOf(std.getBirthDay().toString()));
        csmt.setString(7, std.getAddress());
        csmt.setString(8, std.getBirthPlace());
        csmt.setString(9, std.getPersonalID());
        csmt.setString(10, std.getPhoneNumber());
        csmt.setString(11, std.getEmail());
        csmt.setString(12, std.getFatherName());
        csmt.setString(13, std.getFatherJob());
        csmt.setString(14, std.getMotherName());
        csmt.setString(15, std.getMotherJob());
        csmt.setString(16, std.getParentPhone());
        csmt.setFloat(17, 0);
        if (std.getStartDate() != null) {
            csmt.setDate(18, java.sql.Date.valueOf(std.getStartDate().toString()));
        } else {
            csmt.setNull(18, java.sql.Types.DATE);
        }
        if (std.getEndDate() != null) {
            csmt.setDate(19, java.sql.Date.valueOf(std.getEndDate().toString()));
        } else {
            csmt.setNull(19, java.sql.Types.DATE);
        }
        boolean result = csmt.execute();
        System.out.println(result);
        sendEmail(std);
        
    }
    
    public static void UpdateStudent(Student std) throws SQLException{
        CallableStatement csmt = DatabaseConnection.con.prepareCall("{call updateStudent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        csmt.setString(1, std.getStudentID());
        csmt.setString(2, std.getFirstName());
        csmt.setString(3, std.getLastName());
        csmt.setString(4, std.getCourse());
        csmt.setString(5, std.getBatch());
        csmt.setString(6, ((std.getGender()==Gender.Male)?"Male":"Female"));
        csmt.setDate(7, java.sql.Date.valueOf(std.getBirthDay().toString()));
        csmt.setString(8, std.getAddress());
        csmt.setString(9, std.getBirthPlace());
        csmt.setString(10, std.getPersonalID());
        csmt.setString(11, std.getPhoneNumber());
        csmt.setString(12, std.getEmail());
        csmt.setString(13, std.getFatherName());
        csmt.setString(14, std.getFatherJob());
        csmt.setString(15, std.getMotherName());
        csmt.setString(16, std.getMotherJob());
        csmt.setString(17, std.getParentPhone());
        csmt.setFloat(18, 0);
        if (std.getStartDate() != null) {
            csmt.setDate(19, java.sql.Date.valueOf(std.getStartDate().toString()));
        } else {
            csmt.setNull(19, java.sql.Types.DATE);
        }
        if (std.getEndDate() != null) {
            csmt.setDate(20, java.sql.Date.valueOf(std.getEndDate().toString()));
        } else {
            csmt.setNull(20, java.sql.Types.DATE);
        }
        boolean result = csmt.execute();
        System.out.println(result);
        sendEmail(std);
        
    }
    
    public static boolean DeleteStudent(Student std){
        boolean result = DatabaseConnection.getUpdateResultSet(SQLHelper.DeleteStudent(std.getStudentID()));
        return result;
    } 

    private static void sendEmail(Student std) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetUserPassOfStudent(std));
        if(rs.next()==false){}
        else{
            String content = "<h3>Welcome to Aptech!</h3><br/><p>This is your account in Aptech</p><br/><p><b>Username :</b> <span>"+rs.getString("Username")+"</span></p><br/><p><b>Password :</b> <span>"+rs.getString("Password")+"</span></p>";
            EmailService.sendEmail(std.getEmail(), "Welcome to Aptech", content);
        }     
    }
    
}
