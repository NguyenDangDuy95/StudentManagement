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
public class Subject implements Serializable {

    private String ID;
    private String name;
    private String numberOfLesson;
    private String subjectInfo;
    private String semester;
    private String courseID;
    private String subjectCode;

    public Subject() {
    }

    public Subject(String ID, String name, String numberOfLesson, String subjectInfo, String semester, String courseID, String subjectCode) {
        this.ID = ID;
        this.name = name;
        this.numberOfLesson = numberOfLesson;
        this.subjectInfo = subjectInfo;
        this.semester = semester;
        this.courseID = courseID;
        this.subjectCode= subjectCode;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    
    @Override
    public String toString() {
        return this.name;
    }
}
