/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controllers.StudentController;
import helpers.ServerConnection;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Message;
import models.Student;

/**
 *
 * @author Duy
 */
public class StudentDataStore implements IDataStore {

    @Override
    public Vector<Student> GetItems() {
        Vector<Student> list = new Vector<>();
        try {
            ServerConnection.oos.writeObject(new Message("studentlist"));
            ServerConnection.oos.flush();
            list = (Vector<Student>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean UpdateItemByID(String id) {
        boolean result = false;
        Vector<Student> stdList = StudentController.Instance.getStdList();
        for (Student student : stdList) {
            if (student.getID().equals(id)) {
                try {
                    ServerConnection.oos.writeObject(student);
                    result = ServerConnection.ois.readBoolean();
                } catch (IOException ex) {
                    Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    @Override
    public boolean DeleteItemByID(String id) {
        boolean result = false;
        Vector<Student> stdList = StudentController.Instance.getStdList();
        for (Student student : stdList) {
            if (student.getID().equals(id)) {
                try {
                    //ServerConnection.os.write(id);
                    result = ServerConnection.ois.readBoolean();
                } catch (IOException ex) {
                    Logger.getLogger(StudentDataStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

}
