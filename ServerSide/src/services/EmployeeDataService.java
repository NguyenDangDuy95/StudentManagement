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
import models.Employee;

/**
 *
 * @author Duy
 */
public class EmployeeDataService {

    private static ResultSet rs = null;

    public static Vector<Employee> getEmployeeList() throws SQLException {
        Vector<Employee> empList = new Vector<>();
        rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getFullInfoEmployeeList());
        while (rs.next()) {
            Employee emp = new Employee(
                    rs.getString("EmployeeID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("PositionName"),
                    rs.getInt("SalaryValue"),
                    MyConstants.getGender(rs.getString("Gender")),
                    rs.getDate("BirthDay"),
                    rs.getString("PersonalID"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Addresss"),
                    rs.getString("BirthPlace"),
                    rs.getString("FatherName"),
                    rs.getString("MotherName"),
                    rs.getString("FatherJob"),
                    rs.getString("MotherJob"),
                    rs.getString("ParentPhone"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate")
            );
            empList.addElement(emp);
        }
        return empList;
    }

    public static Employee getEmployeeByID(String id) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getEmployeeByID(id));
        rs.next();
        Employee emp = new Employee(
                rs.getString("EmployeeID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                (rs.getString("PositionName").equals("admin")) ? "Adminstrator" : "Teacher",
                rs.getInt("SalaryValue"),
                MyConstants.getGender(rs.getString("Gender")),
                rs.getDate("BirthDay"),
                rs.getString("PersonalID"),
                rs.getString("Email"),
                rs.getString("PhoneNumber"),
                rs.getString("Addresss"),
                rs.getString("BirthPlace"),
                rs.getString("FatherName"),
                rs.getString("MotherName"),
                rs.getString("FatherJob"),
                rs.getString("MotherJob"),
                rs.getString("ParentPhone"),
                rs.getDate("StartDate"),
                rs.getDate("EndDate")
        );
        return emp;
    }
}
