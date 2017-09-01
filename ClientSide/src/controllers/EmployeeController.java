/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.ServerConnection;
import java.io.IOException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.Employee;
import models.Message;
import models.Request;


/**
 *
 * @author Duy
 */
public class EmployeeController {
    private static EmployeeController _instance = null;
    
    private Vector<Employee> _empList;
    
    public static EmployeeController getInstance(){
        if(_instance == null){
            _instance = new EmployeeController();
        }
        return _instance;
    }
    
    private EmployeeController(){
        _empList = new Vector<>();
    }
    
    public DefaultTableModel getTableModel(){
//        Vector header = new Vector();
//        header.add(Student.COLUMN_ID);
//        header.add(Employee.COLUMN_NAME);
//        header.add(Employee.COLUMN_AGE);
//        header.add(Employee.COLUMN_DEPARTMENT);
//        
//        Vector data = new Vector();
//        for(int i = 0; i < _empList.size(); i++){
//            Employee employee = _empList.get(i);
//            Vector row = new Vector();
//            row.addElement(employee.getId());
//            row.addElement(employee.getName());
//            row.addElement(employee.getAge());
//            row.addElement(employee.getDepartment().getName());
//            data.addElement(row);
//        }
//        
//        DefaultTableModel dataModel = new DefaultTableModel(data, header){
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }            
//        };        
//        return dataModel;
        return null;
    }
    
    public void load(){
        //request to server to get student list       
        try {
            ServerConnection.oos.writeObject(new Message(Request.GetEmployeeList));
            ServerConnection.oos.flush();
            _empList = (Vector<Employee>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            
        }
    }
    
    public Vector<Employee> get(){
        if(_empList.size() == 0){
            load();
        }
        return _empList;
    }
    
    public Employee getEmployeeByID(String id){
        for(Employee emp : _empList)
        {
            if(emp.getID().equals(id)) return emp;
        }
        return null;
    }
    public void add(Employee emp){
        //request server to save to database

        Message mgs = new Message();
        mgs.setTitle(Request.AddMessage);
        mgs.setBody(Request.EmployeeObject);
        mgs.setID(emp.getID());
        mgs.setEmp(emp);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            //Logger.getLogger(EmployeeDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        //save temp to list
        _empList.addElement(emp);
    }
    
    public void update(Employee emp){
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(Request.EmployeeObject);
        mgs.setEmp(emp);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            //Logger.getLogger(BatchDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        //update to list AUTO
        for(Employee employee : _empList)
        {
            if(employee.getID().equals(emp.getID())){ employee = emp; break;}
        }
    }
    
    public void delete(Employee emp){
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage,Request.EmployeeObject,emp.getID());
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            //Logger.getLogger(EmployeeDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        //delete from list
        _empList.removeElement(emp);
    }
}
