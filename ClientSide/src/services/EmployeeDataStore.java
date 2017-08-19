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
import models.Employee;
import models.Student;

/**
 *
 * @author Duy
 */
public class EmployeeDataStore implements IDataStore{

    @Override
    public Vector<Employee> GetItems() {
        Vector<Employee> list = new Vector<>();
        try {
            //ServerConnection.os.write("employeelist");
            list = (Vector<Employee>)ServerConnection.ois.readObject();
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
    public boolean UpdateEmployee(Employee emp)
    {
        boolean result = false;
        try {
            ServerConnection.oos.writeObject(emp);
            ServerConnection.oos.flush();
            result = ServerConnection.ois.readBoolean();
        } catch (Exception e) {
        }
        return result;
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
