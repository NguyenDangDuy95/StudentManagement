/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.MyConstants;
import helpers.SQLHelper;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Vector;
import models.Employee;
import models.Gender;
import models.Student;

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

    public static void UpdateEmployee(Employee emp) throws SQLException {
        CallableStatement csmt = DatabaseConnection.con.prepareCall("{call updateEmployee(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        csmt.setString(1, emp.getEmployeeID());
        csmt.setString(2, emp.getFirstName());
        csmt.setString(3, emp.getLastName());
        csmt.setString(4, emp.getPositionName());
        csmt.setString(5, String.valueOf(emp.getSalaryValue()));
        csmt.setString(6, ((emp.getGender() == Gender.Male) ? "Male" : "Female"));
        csmt.setDate(7, java.sql.Date.valueOf(emp.getBirthDay().toString()));
        csmt.setString(8, emp.getPersonalID());
        csmt.setString(9, emp.getEmail());
        csmt.setString(10, emp.getPhoneNumber());
        csmt.setString(11, emp.getAddress());
        csmt.setString(12, emp.getBirthPlace());
        csmt.setString(13, emp.getFatherName());
        csmt.setString(14, emp.getMotherName());
        csmt.setString(15, emp.getFatherJob());
        csmt.setString(16, emp.getMotherJob());
        csmt.setString(17, emp.getParentPhone());
        if (emp.getStartDate() != null) {
            csmt.setDate(18, java.sql.Date.valueOf(emp.getStartDate().toString()));
        } else {
            csmt.setNull(18, java.sql.Types.DATE);
        }
        if (emp.getEndDate() != null) {
            csmt.setDate(18, java.sql.Date.valueOf(emp.getEndDate().toString()));
        } else {
            csmt.setNull(19, java.sql.Types.DATE);
        }
        boolean result = csmt.execute();
        System.out.println(result);
    }
    
    public static void DeleteEmployee(Employee emp){
        DatabaseConnection.getUpdateResultSet(SQLHelper.DeleteEmployee(emp.getEmployeeID()));
    }
    
    public static void AddEmployee(Employee emp) throws SQLException {
        CallableStatement csmt = DatabaseConnection.con.prepareCall("{call addEmployee(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        csmt.setString(1, emp.getFirstName());
        csmt.setString(2, emp.getLastName());
        csmt.setString(3, emp.getPositionName());
        csmt.setString(4, String.valueOf(emp.getSalaryValue()));
        csmt.setString(5, ((emp.getGender() == Gender.Male) ? "Male" : "Female"));
        csmt.setDate(6, java.sql.Date.valueOf(emp.getBirthDay().toString()));
        csmt.setString(7, emp.getPersonalID());
        csmt.setString(8, emp.getEmail());
        csmt.setString(9, emp.getPhoneNumber());
        csmt.setString(10, emp.getAddress());
        csmt.setString(11, emp.getBirthPlace());
        csmt.setString(12, emp.getFatherName());
        csmt.setString(13, emp.getMotherName());
        csmt.setString(14, emp.getFatherJob());
        csmt.setString(15, emp.getMotherJob());
        csmt.setString(16, emp.getParentPhone());
        if (emp.getStartDate() != null) {
            csmt.setDate(17, java.sql.Date.valueOf(emp.getStartDate().toString()));
        } else {
            csmt.setNull(17, java.sql.Types.DATE);
        }
        if (emp.getEndDate() != null) {
            csmt.setDate(18, java.sql.Date.valueOf(emp.getEndDate().toString()));
        } else {
            csmt.setNull(18, java.sql.Types.DATE);
        }
        boolean result = csmt.execute();
        System.out.println(result);
    }
}
