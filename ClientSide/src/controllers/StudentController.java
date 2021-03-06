/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.ServerConnection;
import java.io.IOException;
import java.sql.Date;
import models.Student;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.Batch;
import models.Gender;
import models.Message;
import models.Request;

/**
 *
 * @author Duy
 */
public class StudentController {

    private static StudentController _instance = null;

    private Vector<Student> _stdList;

    public static StudentController getInstance() {
        if (_instance == null) {
            _instance = new StudentController();
        }
        return _instance;
    }

    private StudentController() {
        _stdList = new Vector<>();
    }

    public DefaultTableModel getInfoTableModel(Student std) {
        Vector header = new Vector();
        header.add("Properties");
        header.add("Value");
        Vector data = new Vector();
        getInfoRow(data, "First Name", std.getFirstName());
        getInfoRow(data, "Last Name", std.getLastName());
        getInfoRow(data, "Course", CourseController.getInstance().getCourseByID(std.getCourse()).getName());
        getInfoRow(data, "Batch", BatchController.getInstance().getBatchByID(std.getBatch()).getName());
        getInfoRow(data, "Gender", (std.getGender().equals(Gender.Male)) ? "Male" : "Female");
        getInfoRow(data, "Birth Day", std.getBirthDay());
        getInfoRow(data, "Birth Place", std.getBirthPlace());
        getInfoRow(data, "Address", std.getAddress());
        getInfoRow(data, "Personal ID", std.getPersonalID());
        getInfoRow(data, "Phone Number", std.getPhoneNumber());
        getInfoRow(data, "Email", std.getEmail());
        getInfoRow(data, "Father Name", std.getFatherName());
        getInfoRow(data, "Father Job", std.getFatherJob());
        getInfoRow(data, "Mother Name", std.getMotherName());
        getInfoRow(data, "Mother Job", std.getMotherJob());
        getInfoRow(data, "Parent Phone", std.getParentPhone());
        getInfoRow(data, "Scholarship", (std.getSchoolarship()==null)?"N/A":std.getSchoolarship());
        getInfoRow(data, "Start Date", std.getStartDate());
        getInfoRow(data, "End Date", (std.getEndDate()== null)?"Still studying":std.getEndDate());
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    public DefaultTableModel getAddTableModel() {
        Vector header = new Vector();
        header.add("Properties");
        header.add("Value");
        Vector data = new Vector();
        getInfoRow(data, "First Name", "");
        getInfoRow(data, "Last Name", "");
        getInfoRow(data, "Gender", "");
        getInfoRow(data, "Birth Day", "");
        getInfoRow(data, "Birth Place", "");
        getInfoRow(data, "Address", "");
        getInfoRow(data, "Personal ID", "");
        getInfoRow(data, "Phone Number", "");
        getInfoRow(data, "Email", "");
        getInfoRow(data, "Father Name", "");
        getInfoRow(data, "Father Job", "");
        getInfoRow(data, "Mother Name", "");
        getInfoRow(data, "Mother Job", "");
        getInfoRow(data, "Parent Phone", "");
        getInfoRow(data, "Scholarship", "");
        getInfoRow(data, "Start Date", "");
        getInfoRow(data, "End Date", "");
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }
    
    private void getInfoRow(Vector data, String prop, Object value) {
        Vector row = new Vector();
        row.add(prop);
        row.addElement(value);
        data.add(row);
    }

    public DefaultTableModel getTableModelByList(Vector<Student> stdList) {
        Vector header = new Vector();
        header.add("First Name");
        header.add("Last Name");
        header.add("Class");
        header.add("Course");
        header.add("Birth Day");
        header.add("Gender");
        header.add("Phone Number");

        Vector data = new Vector();
        for (Student std : stdList) {
            Vector row = new Vector();
            row.addElement(std.getFirstName());
            row.addElement(std.getLastName());
            row.addElement(BatchController.getInstance().getBatchByID(std.getBatch()));
            row.addElement(CourseController.getInstance().getCourseByID(std.getCourse()));
            row.addElement(std.getBirthDay());
            row.addElement(std.getGender());
            row.addElement(std.getPhoneNumber());
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }
    public Student getStudentFromDataList(Vector<String> data,Batch batch){
        Date birthDay,startDate,endDate;
        try{
            birthDay = Date.valueOf(data.elementAt(3));
        }catch(Exception e){
            birthDay = null;
        }
        try{
            startDate = Date.valueOf(data.elementAt(15));
        }catch(Exception e){
            startDate = null;
        }
        try{
            endDate = Date.valueOf(data.elementAt(16));
        }catch(Exception e){
            endDate = null;
        }
        Student std = new Student(
                "",
                data.elementAt(0),
                data.elementAt(1),
                batch.getCourseID(),
                batch.getId(),
                (data.elementAt(2).equals("Male")?Gender.Male:Gender.Female),
                birthDay,
                data.elementAt(5),
                data.elementAt(4),
                data.elementAt(6),
                data.elementAt(7),
                data.elementAt(8),
                data.elementAt(9),
                data.elementAt(10),
                data.elementAt(11),
                data.elementAt(12),
                data.elementAt(13),
                data.elementAt(14),
                startDate,
                endDate
        );
        return std;       
    }
    public void load() {
        //request to server to get student list       
        try {
            ServerConnection.oos.writeObject(new Message(Request.GetStudentList));
            ServerConnection.oos.flush();
            _stdList = (Vector<Student>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

        }
    }

    public Vector<Student> get() {
        if (_stdList.isEmpty()) {
            load();
        }
        return _stdList;
    }

    public Student getStudentByID(String id) {
        for (Student std : _stdList) {
            if (std.getStudentID().equals(id)) {
                return std;
            }
        }
        return null;
    }

    public void add(Student std) {
        //request server to save to database
        Message mgs = new Message(Request.AddMessage, Request.StudentObject);
        mgs.setStudent(std);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }
        //save temp to list
        _stdList.addElement(std);
    }

    public void update(Student std) {
        //request server to update to database
        Message mgs = new Message(Request.UpdateMessage, Request.StudentObject);
        mgs.setStudent(std);
        try {
            ServerConnection.oos.writeObject(std);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //update to list AUTO
        for (Student student : _stdList) {
            if (student.getStudentID().equals(std.getStudentID())) {
                student = std;
                break;
            }
        }
    }

    public void delete(Student std) {
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage, Request.StudentObject);
        mgs.setStudent(std);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //delete from list
        _stdList.removeElement(std);
    }

}
