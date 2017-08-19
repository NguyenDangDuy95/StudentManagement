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
import models.Student;
import models.Subject;

/**
 *
 * @author Duy
 */
public class SubjectDataStore implements IDataStore{

    @Override
    public Vector<Subject> GetItems() {
        Vector<Subject> list = new Vector<>();
        try {
            //ServerConnection.os.write("subjectlist");
            //ServerConnection.os.flush();
            list = (Vector<Subject>)ServerConnection.ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
//    public Vector<Subject> getItemsByCourse(String courseID)
//    {
//        Vector<Subject> list = new Vector<>();
//        try {
//            ServerConnection.os.write("subjectof"+courseID);
//            ServerConnection.os.flush();
//            list = (Vector<Subject>)ServerConnection.ois.readObject();
//        } catch (Exception e) {
//        }
//        return list;
//    }
    @Override
    public boolean UpdateItemByID(String id) {
        throw new UnsupportedOperationException("Not support yet.");
    }

    @Override
    public boolean DeleteItemByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean UpdateItem()
    {
        throw new UnsupportedOperationException("Not support yet.");
    }
    
}
