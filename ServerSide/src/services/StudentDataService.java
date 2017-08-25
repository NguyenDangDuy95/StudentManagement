/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import models.Gender;
import models.Score;
import models.Student;

/**
 *
 * @author Duy
 */
public class StudentDataService {

    public static Vector<Student> getStudentList() throws SQLException {
        Vector<Student> stdList = new Vector<>();
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetStudentList);
        while (rs.next()) {
            Student std = new Student(rs.getString("StudentID"),
                    rs.getString("ClassID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    getGender(rs.getString("Gender")),
                    rs.getDate("Birthday"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email")
            );
            stdList.add(std);
        };
        return stdList;
    }

    private static Gender getGender(String gender) {
        if (gender.equalsIgnoreCase("man")) {
            return Gender.Male;
        }
        return Gender.Female;
    }

    public static Student getStudentByID(String id) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetStudentByID(id));
        rs.next();
        Student std = new Student(
                rs.getString("StudentID"),
                rs.getString("ClassID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                getGender(rs.getString("Gender")),
                rs.getDate("Birthday"),
                rs.getString("IDCard"),
                rs.getString("PhoneNumber"),
                rs.getString("Address"),
                rs.getString("PlaceOfBirth"),
                rs.getString("Email"),
                rs.getString("Schoolarship"),
                rs.getString("CourseID"),
                rs.getDate("StartDate"),
                rs.getDate("EndDate"),
                rs.getString("FatherName"),
                rs.getString("MotherName"),
                rs.getString("FatherJob"),
                rs.getString("MotherJob"),
                rs.getString("ParentPhone")
        );
        return std;
    }

    public static Vector<Score> getScoreOfStudent(String id) {
        return null;
    }
}
