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
import models.Employee;
import models.Student;
import models.Subject;
import services.CourseDataService;
import services.EmployeeDataService;
import services.StudentDataService;

/**
 *
 * @author Duy
 */
public class TestDataService {
    public static void main(String[] args) {
        DatabaseConnection.getConnection();
        Vector<Employee> testList = new Vector<Employee>();
        try {
            testList = EmployeeDataService.getEmployeeList();
            for(Employee emp : testList){
                System.out.println(emp.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
