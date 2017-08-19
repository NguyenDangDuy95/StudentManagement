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
public class Score implements Serializable{
    private String studentID;
    private String subjectID;
    private int numberOfExam;
    private double theoryScore;
    private double actScore;

    public Score() {
    }

    public Score(String studentID, String subjectID, int numberOfExam, double theoryScore, double actScore) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.numberOfExam = numberOfExam;
        this.theoryScore = theoryScore;
        this.actScore = actScore;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getNumberOfExam() {
        return numberOfExam;
    }

    public void setNumberOfExam(int numberOfExam) {
        this.numberOfExam = numberOfExam;
    }

    public double getTheoryScore() {
        return theoryScore;
    }

    public void setTheoryScore(double theoryScore) {
        this.theoryScore = theoryScore;
    }

    public double getActScore() {
        return actScore;
    }

    public void setActScore(double actScore) {
        this.actScore = actScore;
    }
    
}
