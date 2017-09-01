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
        System.out.println(EmailService.sendEmail("duy2112@gmail.com", "ahihi", "hello duy"));
    }
}
