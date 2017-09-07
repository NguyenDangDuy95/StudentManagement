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
import models.Batch;
import models.Course;
import models.Student;
import models.Subject;
import services.CourseDataService;
import services.StudentDataService;

/**
 *
 * @author Duy
 */
public class TestDataService {
    public static void main(String[] args) {
        DatabaseConnection.getConnection();
        Vector<Course> stdList = new Vector<Course>();
        try {
            stdList = CourseDataService.getCourseList();
            for(Course course : stdList){
                System.out.println(course.getName());
                for(Batch batch : course.getBatchList()){
                    System.out.println("\t"+batch.getName());
                    for(Student std: batch.getStdList()){
                        System.out.println("\t\t"+std.toString());
                    }
                }
            }
            for(Course course : stdList){
                System.out.println(course.getName());
                for(Subject subject : course.getSubjectList()){
                    System.out.println("\t"+subject.getName() + subject.getSubjectInfo());
                }
            }
            Student std = StudentDataService.GetStudentByID("BB738174-12DF-4B8F-B020-020B4F6FF4E0");
            System.out.println(std.toString());
        } catch (SQLException ex) {
            Logger.getLogger(TestDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
