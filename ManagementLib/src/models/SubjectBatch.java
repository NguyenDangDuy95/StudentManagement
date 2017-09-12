/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DUY
 */
public class SubjectBatch implements Serializable{
    private Employee teacher;
    private Subject subject;
    private String batchID;
    private Date startDate;
    private Date endDate;

    public SubjectBatch() {
    }

    public SubjectBatch(Employee teacher, Subject subject, String batchID, Date startDate, Date endDate) {
        this.teacher = teacher;
        this.subject = subject;
        this.batchID = batchID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
