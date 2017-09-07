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
import models.Message;
import models.Request;
import models.Verification;

/**
 *
 * @author DUY
 */
public class VerificationService {

    public static Message checkValidation(Verification user) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getAccountList());
        Message mgs = new Message();
        while (rs.next()) {
            System.out.println(rs.getString("Username"));
            if (user.getUsername().equals(rs.getString("Username"))) {
                if (user.getPassword().equals(rs.getString("Password").trim())) {
                    mgs.setTitle(Request.SuccessMessage);
                    switch (rs.getString("UserRoles")) {
                        case "student":
                            mgs.setBody(Request.StudentObject);
                            mgs.setStudent(StudentDataService.GetStudentByID(rs.getString("UserID")));
                            break;
                        case "employee":
                            mgs.setBody(Request.EmployeeObject);
                            mgs.setEmployee(EmployeeDataService.getEmployeeByID(rs.getString("UserID")));
                            break;
                        case "admin":
                            mgs.setBody(Request.AdminObject);
                            mgs.setEmployee(EmployeeDataService.getEmployeeByID(rs.getString("UserID")));
                            break;
                    }
                    break;
                } else {
                    mgs.setTitle(Request.FailedMessage);
                    mgs.setBody(Request.Password);
                    break;
                }
            } else {
                if (rs.isLast()) {
                    mgs.setTitle(Request.FailedMessage);
                    mgs.setBody(Request.Username);
                }
            }
        }
        return mgs;

    }

}
