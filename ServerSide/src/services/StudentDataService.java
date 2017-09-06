/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.MyConstants;
import helpers.SQLHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import models.Student;

/**
 *
 * @author Duy
 */
public class StudentDataService {

    public static Vector<Student> GetStudentListByBatchID(String batchID) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getStudentListByBatchID(batchID));
        Vector<Student> stdList = new Vector<>();
        while (rs.next()) {
            Student std = new Student(
                    rs.getString("StudentID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Course"),
                    rs.getString("Batch"),
                    MyConstants.getGender(rs.getString("Gender")),
                    rs.getDate("BirthDay"),
                    rs.getString("Address"),
                    rs.getString("BirthPlace"),
                    rs.getString("PersonalID"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email"),
                    rs.getString("FatherName"),
                    rs.getString("FatherJob"),
                    rs.getString("MotherName"),
                    rs.getString("MotherJob"),
                    rs.getString("ParentPhone"),
                    rs.getString("Schoolarship"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate")
            );
            stdList.addElement(std);
        }
        return stdList;
    }
}
