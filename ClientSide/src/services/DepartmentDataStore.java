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
import models.Department;
import models.Student;

/**
 *
 * @author Duy
 */
public class DepartmentDataStore implements IDataStore{

    @Override
    public Vector<Department> GetItems() {
        Vector<Department> list = new Vector<>();
        try {
            //ServerConnection.os.write("departmentlist");
            //ServerConnection.os.flush();
            list = (Vector<Department>)ServerConnection.ois.readObject();
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
        boolean result = false;
        try {
            //ServerConnection.os.write(id);
            //ServerConnection.os.flush();
            result = ServerConnection.ois.readBoolean();
            
        } catch (Exception e) {
        }
        return result;
    }
    
}
