/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import java.sql.ResultSet;
import java.util.Vector;
import models.Employee;

/**
 *
 * @author Duy
 */
public class EmployeeDataService {
    private static ResultSet rs = null;
    public static Vector<Employee> getEmployeeList(){
        Vector<Employee> empList = new Vector<>();
        rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetEmployeeList);
    }
}
