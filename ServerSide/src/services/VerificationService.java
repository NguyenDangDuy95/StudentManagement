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
import models.UserProfile;
import models.Verification;

/**
 *
 * @author DUY
 */
public class VerificationService {

    public static Message checkValidation(Verification user) throws SQLException {
        String userName = user.getUsername();
        String password = user.getPassword();

        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getAccountList());
        Message mgs = new Message();
        //Username, Password, UserID, UserRoles
        mgs.setTitle(Request.FailedMessage);
        mgs.setBody(Request.Username);
        Vector<UserProfile> userList = getUserProfile(rs);

        for (UserProfile profile : userList) {
            System.out.println(profile.getUsername());
            if (userName.equals(profile.getUsername())) {
                if (profile.getPassword().trim().equals(password)) {
                    if (profile.getUserRole().equals(Request.EmployeeObject)) {
                        mgs.setTitle(Request.SuccessMessage);
                        mgs.setBody(Request.EmployeeObject);
                        mgs.setEmployee(EmployeeDataService.getEmployeeByID(profile.getUserID()));
                    }
                    if (profile.getUserRole().equals(Request.AdminObject)) {
                        mgs.setTitle(Request.SuccessMessage);
                        mgs.setBody(Request.AdminObject);
                        mgs.setEmployee(EmployeeDataService.getEmployeeByID(profile.getUserID()));
                    }
                    if (profile.getUserRole().equals(Request.StudentObject)) {
                        mgs.setTitle(Request.SuccessMessage);
                        mgs.setBody(Request.StudentObject);
                        mgs.setStudent(StudentDataService.GetStudentByID(profile.getUserID()));
                    }
                    break;
                } else {
                    mgs.setTitle(Request.FailedMessage);
                    mgs.setBody(Request.Password);
                    break;
                }
            }
        }

        return mgs;
    }

    private static Vector<UserProfile> getUserProfile(ResultSet rs) throws SQLException {
        Vector<UserProfile> userList = new Vector<>();
        while (rs.next()) {
            UserProfile user = new UserProfile(
                    rs.getString("UserName"),
                    rs.getString("Password"),
                    rs.getString("UserID"),
                    rs.getString("UserRoles"));
            userList.add(user);
        }
        return userList;
    }

}
