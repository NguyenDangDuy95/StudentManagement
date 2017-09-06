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
public class Message implements Serializable {

    private String Title = "";
    private String Body = "";
    
    private Student student;
    private Employee employee;
    private Subject subject;
    private Batch batch;
    private Course course;
    

    public Message() {
    }

    public Message(String title) {
        this.Title = title;
    }

    public Message(String title, String body) {
        this.Title = title;
        this.Body = body;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
