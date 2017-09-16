/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.MyConstants;
import helpers.ServerConnection;
import java.io.IOException;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.Employee;
import models.Gender;
import models.Message;
import models.Request;
import models.Student;
import test.ClientSideMain;

/**
 *
 * @author Duy
 */
public class EmployeeController {

    private static EmployeeController _instance = null;

    private Vector<Employee> _empList;

    public static EmployeeController getInstance() {
        if (_instance == null) {
            _instance = new EmployeeController();
        }
        return _instance;
    }

    private EmployeeController() {
        _empList = new Vector<>();
    }

    public DefaultTableModel getInfoTableModel(Employee emp) {
        Vector header = new Vector();
        header.add("Properties");
        header.add("Value");
        Vector data = new Vector();
        getInfoRow(data, "First Name", emp.getFirstName());
        getInfoRow(data, "Last Name", emp.getLastName());
        getInfoRow(data, "Position Name", (emp.getPositionName().equals("admin")) ? MyConstants.AdminRole : MyConstants.TeacherRole);
        if (ClientSideMain.CurrentUserRole.equals(Request.AdminObject)) {
            getInfoRow(data, "Salary", emp.getSalaryValue());
        } else if (ClientSideMain.CurrentUserRole.equals(Request.EmployeeObject)) {
            Employee currentUser = (Employee) ClientSideMain.CurrentUser;
            if (currentUser.getEmployeeID().equals(emp.getEmployeeID())) {
                getInfoRow(data, "Salary", emp.getSalaryValue());
            }
        }
        getInfoRow(data, "Gender", (emp.getGender().equals(Gender.Male)) ? "Male" : "Female");
        getInfoRow(data, "Birth Day", emp.getBirthDay());
        getInfoRow(data, "Birth Place", emp.getBirthPlace());
        getInfoRow(data, "Address", emp.getAddress());
        getInfoRow(data, "Personal ID", emp.getPersonalID());
        getInfoRow(data, "Phone Number", emp.getPhoneNumber());
        getInfoRow(data, "Email", emp.getEmail());
        getInfoRow(data, "Father Name", emp.getFatherName());
        getInfoRow(data, "Father Job", emp.getFatherJob());
        getInfoRow(data, "Mother Name", emp.getMotherName());
        getInfoRow(data, "Mother Job", emp.getMotherJob());
        getInfoRow(data, "Parent Phone", emp.getParentPhone());
        getInfoRow(data, "Start Date", emp.getStartDate());
        getInfoRow(data, "End Date", (emp.getEndDate() == null) ? "N/A" : emp.getEndDate());
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    public Employee getEmployeeFromDataList(Vector<String> data,Employee user){
        Date birthDay,startDate,endDate;
        try{
            birthDay = Date.valueOf(data.elementAt(5));
        }catch(Exception e){
            birthDay = null;
        }
        try{
            startDate = Date.valueOf(data.elementAt(16));
        }catch(Exception e){
            startDate = null;
        }
        try{
            endDate = Date.valueOf(data.elementAt(17));
        }catch(Exception e){
            endDate = null;
        }
        Employee emp = new Employee(
                user.getEmployeeID(),
                data.elementAt(0),
                data.elementAt(1),
                data.elementAt(2),
                Integer.valueOf(data.elementAt(3)),
                (data.elementAt(4).equals("Male")?Gender.Male:Gender.Female),
                birthDay,
                data.elementAt(8),
                data.elementAt(10),
                data.elementAt(9),
                data.elementAt(7),
                data.elementAt(6),
                data.elementAt(11),
                data.elementAt(13),
                data.elementAt(12),
                data.elementAt(14),
                data.elementAt(15),
                startDate,
                endDate);
        return emp;
    }
    
    private void getInfoRow(Vector data, String prop, Object value) {
        Vector row = new Vector();
        row.add(prop);
        row.addElement(value);
        data.add(row);
    }

    public DefaultTableModel getTableModelByList(Vector<Employee> empList) {
        Vector header = new Vector();
        header.add("First Name");
        header.add("Last Name");
        header.add("Position");
        header.add("Gender");
        header.add("Birth Day");
        header.add("Phone Number");
        header.add("Address");

        Vector data = new Vector();
        for (Employee emp : empList) {
            Vector row = new Vector();
            row.addElement(emp.getFirstName());
            row.addElement(emp.getLastName());
            row.addElement(emp.getPositionName());
            row.addElement((emp.getGender() == Gender.Male) ? "Male" : "Female");
            row.addElement(emp.getBirthDay());
            row.addElement(emp.getPhoneNumber());
            row.addElement(emp.getAddress());
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    public void load() {
        //request to server to get student list       
        try {
            ServerConnection.oos.writeObject(new Message(Request.GetEmployeeList));
            ServerConnection.oos.flush();
            _empList = (Vector<Employee>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

        }
    }

    public Vector<Employee> get() {
        if (_empList.size() == 0) {
            load();
        }
        return _empList;
    }

    public Employee getEmployeeByID(String id) {
        for (Employee emp : _empList) {
            if (emp.getEmployeeID().equals(id)) {
                return emp;
            }
        }
        return null;
    }

    public Vector<Employee> getAdminList() {
        Vector<Employee> list = new Vector<>();
        for (Employee emp : _empList) {
            if (emp.getPositionName().equals("admin")) {
                list.add(emp);
            }
        }
        return list;
    }

    public Vector<Employee> getTeacherList() {
        Vector<Employee> list = new Vector<>();
        for (Employee emp : _empList) {
            if (emp.getPositionName().equals("teacher")) {
                list.add(emp);
            }
        }
        return list;
    }

    public void add(Employee emp) {
        //request server to save to database

        Message mgs = new Message();
        mgs.setTitle(Request.AddMessage);
        mgs.setBody(Request.EmployeeObject);
        mgs.setEmployee(emp);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            //Logger.getLogger(EmployeeDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        //save temp to list
        _empList.addElement(emp);
    }

    public void update(Employee emp) {
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(Request.EmployeeObject);
        mgs.setEmployee(emp);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            //Logger.getLogger(BatchDataStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        //update to list AUTO
        for (Employee employee : _empList) {
            if (employee.getEmployeeID().equals(emp.getEmployeeID())) {
                employee = emp;
                break;
            }
        }
    }

    public void delete(Employee emp) {
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage, Request.EmployeeObject);
        mgs.setEmployee(emp);
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
