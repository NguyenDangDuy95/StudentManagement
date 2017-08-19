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
public class Message implements Serializable{

    private String Title;
    private String Body;
    private Student std;
    private Employee emp;
    private Course cor;
    private Department dpm;
    private Subject sub;
    private Batch bat;
    
    public Message() {
    }

    public Message(String Title, String Body) {
        this.Title = Title;
        this.Body = Body;
    }

    public Message(String Title, Student std) {
        this.Title = Title;
        this.std = std;
    }
    
    public Message(String Title, Employee emp)
    {
        this.Title = Title;
        this.emp = emp;
    }
    public Message(String Title, Subject sub)
    {
        this.Title=Title;
        this.sub = sub;
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

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

}
