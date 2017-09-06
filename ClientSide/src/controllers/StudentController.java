/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.ServerConnection;
import java.io.IOException;
import models.Student;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.Message;
import models.Request;


/**
 *
 * @author Duy
 */

public class StudentController {
    private static StudentController _instance = null;
    
    private Vector<Student> _stdList;
    
    public static StudentController getInstance(){
        if(_instance == null){
            _instance = new StudentController();
        }
        return _instance;
    }
    
    private StudentController(){
        _stdList = new Vector<>();
    }
    
    public DefaultTableModel getTableModel(){
//        Vector header = new Vector();
//        header.add(Student.COLUMN_ID);
//        header.add(Employee.COLUMN_NAME);
//        header.add(Employee.COLUMN_AGE);
//        header.add(Employee.COLUMN_DEPARTMENT);
//        
//        Vector data = new Vector();
//        for(int i = 0; i < _stdList.size(); i++){
//            Employee employee = _stdList.get(i);
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
            ServerConnection.oos.writeObject(new Message(Request.GetStudentList));
            ServerConnection.oos.flush();
            _stdList = (Vector<Student>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            
        }
    }
    
    public Vector<Student> get(){
        if(_stdList.isEmpty()){
            load();
        }
        return _stdList;
    }
    
    public Student getStudentByID(String id){
        for(Student std : _stdList)
        {
            if(std.getStudentID().equals(id)) return std;
        }
        return null;
    }
    public void add(Student std){
        //request server to save to database
        try {
            ServerConnection.oos.writeObject(std);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            
        }
        //save temp to list
        _stdList.addElement(std);
    }
    
    public void update(Student std){
        //request server to update to database
        try {
            ServerConnection.oos.writeObject(std);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
          
        }

        //update to list AUTO
        for(Student student : _stdList)
        {
            if(student.getStudentID().equals(std.getStudentID())){ student = std; break;}
        }
    }
    
    public void delete(Student std){
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage+Request.StudentObject, std.getStudentID());
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
     
        }

        //delete from list
        _stdList.removeElement(std);
    }
    

}
