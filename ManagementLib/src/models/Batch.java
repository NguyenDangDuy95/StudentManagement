/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Duy
 */
public class Batch implements Serializable{

    private String id;
    private String name;
    private String courseID;
    private Vector<Student> stdList;
    private Employee currentTeacher;
    private Subject currentSubject;
    private Vector<Score> scList;
    private Vector<Attendance> attList;
    private Vector<SubjectBatch> sbList;

    public Batch() {
    }

    public Batch(String id, String name, String courseID) {
        this.id = id;
        this.name = name;
        this.courseID = courseID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Vector<Student> getStdList() {
        return stdList;
    }

    public void setStdList(Vector<Student> stdList) {
        this.stdList = stdList;
    }

    public Employee getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(Employee currentTeacher) {
        this.currentTeacher = currentTeacher;
    }

    public Subject getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(Subject currentSubject) {
        this.currentSubject = currentSubject;
    }

    public Vector<Score> getScList() {
        return scList;
    }

    public void setScList(Vector<Score> scList) {
        this.scList = scList;
    }

    public Vector<Attendance> getAttList() {
        return attList;
    }

    public void setAttList(Vector<Attendance> attList) {
        this.attList = attList;
    }

    public Vector<SubjectBatch> getSbList() {
        return sbList;
    }

    public void setSbList(Vector<SubjectBatch> sbList) {
        this.sbList = sbList;
    }
    
    
    @Override
    public String toString() {
        return this.name;
    }
}
