/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DUY
 */
public class UserProfile {
    private String username;
    private String password;
    private String userID;
    private String userRole;

    public UserProfile() {
    }

    public UserProfile(String username, String password, String userID, String userRolie) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.userRole = userRolie;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
