package test;

import helpers.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Duy
 */
public class ServerSideMain {
    public static void main(String[] args) {
        try {
            new Server().serve();
        } catch (IOException ex) {
            Logger.getLogger(ServerSideMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
