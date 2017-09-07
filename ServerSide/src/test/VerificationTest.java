/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helpers.DatabaseConnection;
import java.sql.SQLException;
import java.util.Scanner;
import models.Message;
import models.Verification;
import services.VerificationService;

/**
 *
 * @author DUY
 */
public class VerificationTest {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.getConnection();
        Scanner s = new Scanner(System.in);
        System.out.println("Username: ");
        String username = s.nextLine();
        System.out.println("Password");
        String password = s.nextLine();
        Message mgs = VerificationService.checkValidation(new Verification(username, password));
        System.out.println(mgs.getTitle()+"\t"+mgs.getBody());
    }
}
