/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import services.EmailService;

/**
 *
 * @author Duy
 */
public class EmailTest {
    public static void main(String[] args) {
        String content = "<h3>Welcome to Aptech!</h3><br/><p>This is your account in Aptech</p><br/><p><b>Username :</b> <span></span></p><br/><p><b>Password :</b> <span></span></p><br/>";
        
        //EmailService.sendEmail(std.getEmail(), "Welcome to Aptech", content);
        System.out.println(EmailService.sendEmail("duy2112@gmail.com", "ahihi", content));
    }
}
