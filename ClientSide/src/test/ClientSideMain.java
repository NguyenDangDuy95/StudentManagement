/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controllers.StudentController;
import helpers.MyConstants;
import helpers.ServerConnection;
import userControls.CustomUIManager;
import views.SplashScreen;

/**
 *
 * @author Duy
 */
public class ClientSideMain {
    public static String CurrentState;
    public static Object CurrentUser;
    public static String CurrentUserRole;
    public static void main(String[] args) {
        CustomUIManager.customUI();
        
        ServerConnection.connect();
        new SplashScreen().showSplash();
    }
}
