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
import models.Course;
import models.Message;
import models.Request;

/**
 *
 * @author Duy
 */
public class CourseController {

    private static CourseController _instance = null;

    private Vector<Course> _courseList;

    public static CourseController getInstance() {
        if (_instance == null) {
            _instance = new CourseController();
        }
        return _instance;
    }

    private CourseController() {
        _courseList = new Vector<>();
    }

    public DefaultTableModel getTableModel() {
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

    public void load() {
        //request to server to get student list       
        
        try {
            Message mgs = new Message(Request.GetCourseList);
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
            _courseList = (Vector<Course>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

        }
    }

    public Vector<Course> get() {
        if (_courseList.isEmpty()) {
            load();
        }
        return _courseList;
    }

    public Course getCourseByID(String id) {
        for (Course cor : _courseList) {
            if (cor.getID().equals(id)) {
                return cor;
            }
        }
        return null;
    }

    public void add(Course cor) {
        //request server to save to database

        Message mgs = new Message();
        mgs.setTitle(Request.AddMessage);
        mgs.setBody(Request.CourseObject);
        mgs.setCourse(cor);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
        }
        //save temp to list
        _courseList.addElement(cor);
    }

    public void update(Course cor) {
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(Request.CourseObject);
        mgs.setCourse(cor);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //update to list AUTO
        for (Course course : _courseList) {
            if (course.getID().equals(cor.getID())) {
                course = cor;
                break;
            }
        }
    }

    public void delete(Course course) {
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage, Request.CourseObject);
        mgs.setCourse(course);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
            
        } catch (IOException ex) {

        }

        //delete from list
        _courseList.removeElement(course);
    }

}
