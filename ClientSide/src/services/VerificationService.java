
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.ServerConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Message;
import models.Request;

/**
 *
 * @author Duy
 */
public class VerificationService {
    public static Message verify(String username, String password)
    {
        Message result= new Message();
        Message mgs = new Message(Request.Verification, username.trim()+" "+password.trim());
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
            result = (Message)ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(VerificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}