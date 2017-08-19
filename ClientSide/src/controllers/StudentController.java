/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import models.Student;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import services.StudentDataStore;

/**
 *
 * @author Duy
 */

public class StudentController {
    private Vector<Student> stdList;

    public Vector<Student> getStdList() {
        return stdList;
    }
    public static StudentController Instance;
    public StudentController() {
        stdList = new Vector<Student>();
        Instance = this;
    }
    
    public void load()
    {
        stdList = new StudentDataStore().GetItems();
    }
    
    public DefaultTableModel getSelectedStudentInfomationDataModel()
    {
        Vector header = new Vector();
        header.addElement("Key");
        header.addElement("Value");
        Vector data = new Vector();
        //for
        Vector row = new Vector();
        row.addElement("ahihi");
        row.addElement("ahuhu");
        data.add(row);
        return new DefaultTableModel(data, header);
    }
}
