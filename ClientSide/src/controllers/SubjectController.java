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
import models.Message;
import models.Request;
import models.Subject;

/**
 *
 * @author DUY
 */
public class SubjectController {

    public static DefaultTableModel getTableModel() {

        return null;
    }

    public static DefaultTableModel getTableModelByList(Vector<Subject> subList) {
        Vector header = new Vector();
        header.addElement("Subject Name");
        header.addElement("Number Of Lessons");
        header.addElement("Semester");
        header.addElement("Course");
        header.addElement("Infomation");
        Vector data = new Vector();
        for(Subject sub : subList){
            Vector row = new Vector();
            row.addElement(sub.getName());
            row.addElement(sub.getNumberOfLesson());
            row.addElement("Semester " + sub.getSemester());
            row.addElement(CourseController.getInstance().getCourseByID(sub.getCourseID()).getName());
            row.addElement(sub.getNumberOfLesson());
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    public static void add(Subject sub) {
        //request server to save to database
        try {
            ServerConnection.oos.writeObject(sub);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }
        CourseController.getInstance().getCourseByID(sub.getCourseID()).getSubjectList().add(sub);
    }

    public static void update(Subject sub) {
        //request server to update to database
        try {
            ServerConnection.oos.writeObject(sub);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }
        for (Subject subject : CourseController.getInstance().getCourseByID(sub.getCourseID()).getSubjectList()) {
            if (subject.getID().equals(sub.getID())) {
                subject = sub;
            }
        }
    }

    public static void delete(Subject sub) {
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage + Request.SubjectObject, sub.getID());
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        CourseController.getInstance().getCourseByID(sub.getCourseID()).getSubjectList().removeElement(sub);
    }

}
