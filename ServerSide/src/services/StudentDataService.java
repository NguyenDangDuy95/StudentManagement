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
        csmt.setDate(18, java.sql.Date.valueOf(std.getStartDate().toString()));
        csmt.setDate(19, java.sql.Date.valueOf(std.getEndDate().toString()));
        boolean result = csmt.execute();
        System.out.println(result);
    }
}
