/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Duy
 */
public class Subject implements Serializable{
    private String ID;
    private String name;
    private String numberOfLesson;
    private String subjectInfo;
    private Semester semester;
    private String courseID;

    public Subject() {
    }

    public Subject(String ID, String name, String numberOfLesson, String subjectInfo, Semester semester, String courseID) {
        this.ID = ID;
        this.name = name;
        this.numberOfLesson = numberOfLesson;
        this.subjectInfo = subjectInfo;
        this.semester = semester;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(String numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public String getSubjectInfo() {
        return subjectInfo;
    }

    public void setSubjectInfo(String subjectInfo) {
        this.subjectInfo = subjectInfo;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    
    
    
}
