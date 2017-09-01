/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helpers.DatabaseConnection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Course;
import services.CourseDataService;

/**
 *
 * @author Duy
 */
public class TestDataService {
    public static void main(String[] args) {
        DatabaseConnection.getConnection();
        Vector<Course> courseList = new Vector<Course>();
        try {
            courseList = CourseDataService.getCourseList();
            System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(TestDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
