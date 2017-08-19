/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.MyConstants;

/**
 *
 * @author Duy
 */
public class AppController {
    public static AppController Instance;
    private MyConstants.OptionDialogType currentUserRole;
    
    public AppController() {
        Instance = this;
    }
    
    public MyConstants.OptionDialogType getCurrentUserRole()
    {
        return currentUserRole;
    }
    public void setCurrentUserRole(MyConstants.OptionDialogType role)
    {
        currentUserRole = role;
    }
}
