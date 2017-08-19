/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import models.Student;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */

public class StudentController {
    public static final String STUDENTID = "StudentID";
    public static final String BATCHID = "ClassID";
    public static final String LASTNAME = "LastName";
    public static final String FIRSTNAME = "FirstName";
    public static final String GENDER = "Gender";
    public static final String BIRTHDATE = "BirthDate";
    public static final String ADDRESS = "Address";
    public static final String PLACEOFBIRTH = "PlaceOfBirth";
    public static final String FATHERNAME = "FatherName";
    public static final String FATHERJOB = "FatherJob";
    public static final String PARENTPHONE = "ParentPhone";
    public static final String MOTHERJOB = "MotherJob";
    public static final String MOTHERNAME = "MotherName";
    public static final String IDCARD = "IDCard";
    public static final String PHONENUMBER = "PhoneNumber";
    public static final String EMAIL = "Email";
    public static final String SCHOLARSHIP = "Scholarship";
    public static final String COURSE = "Course";
    public static final String STARTDATE = "StartDate";
    public static final String ENDDATE = "EndDate";
    
    private Vector<Student> stdList;

    public Vector<Student> getStdList() {
        return stdList;
    }
    public static StudentController Instance;
    public StudentController() {
        stdList = new Vector<Student>();
        Instance = this;
    }
    
    public void load()
    {
        DatabaseConnection.getExecutedResultSet(SQLHelper.createSelectSQL(SQLHelper.STUDENT_TABLE, "*"));
    }
    
    public static Student getStudentByID(String ID)
    {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getElementByID(ID,"student"));
        Student std = null;
        try {
            std = new Student(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(9), rs.getString(10), rs.getString(11), rs.getDate(12), rs.getDate(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return std;
    }

}
