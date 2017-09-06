/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import models.Gender;

/**
 *
 * @author Duy
 */
public class MyConstants {

    public static Gender getGender(String gender) {
        if (gender.equalsIgnoreCase("man")) {
            return Gender.Male;
        }
        return Gender.Female;
    }
}
