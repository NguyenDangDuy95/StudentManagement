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
import models.Student;

/**
 *
 * @author Duy
 */
public class StudentDataService {
    public static Vector<Student> getStudentList() throws SQLException
    {
        Vector<Student> stdList = new Vector<>();
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetStudentList);
        rs.first();
        do{
            
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
        }while(rs.next());
    }
    private static Gender getGender(String gender)
    {
        if(gender.equalsIgnoreCase("man"))return Gender.Male;
        return Gender.Female;
    }
}
