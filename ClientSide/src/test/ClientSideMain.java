/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import userControls.CustomUIManager;
import views.LoginView;
import views.SplashScreen;

/**
 *
 * @author Duy
 */
public class ClientSideMain {
    public static void main(String[] args) {
        new CustomUIManager();
        //new SplashScreen().showSplash();
        new SplashScreen().showSplash();
    }
}
