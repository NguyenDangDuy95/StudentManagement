/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.ServerConnection;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Batch;
import models.Student;

/**
 *
 * @author Duy
 */
public class BatchDataStore implements IDataStore{

    @Override
    public Vector<Batch> GetItems() {
        Vector<Batch> list = new Vector<>();
        try {
            //ServerConnection.os.write("batchlist");
            list = (Vector<Batch>)ServerConnection.ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public boolean UpdateItemByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DeleteItemByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
