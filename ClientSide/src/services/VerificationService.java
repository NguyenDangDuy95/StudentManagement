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
import models.Verification;

/**
 *
 * @author Duy
 */
public class VerificationService {
    public static String verify(String username, String password)
    {
        String result = "";
        Message input= new Message();
        Verification info = new Verification(username, password);
        Message message = new Message("verify", info);
        try {
            ServerConnection.oos.writeObject(message);
            ServerConnection.oos.flush();
            input = (Message)ServerConnection.ois.readObject();
            result = input.getTitle();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(VerificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
