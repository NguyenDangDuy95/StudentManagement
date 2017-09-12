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
public class Course implements Serializable{
    private String ID;
    private String name;
    private String info;
    private Vector<Batch> batchList; 
    private Vector<Subject> subjectList;

    

    public Course() {
    }

    public Course(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Course(String ID, String name,String info, Vector<Batch> batchList, Vector<Subject> subjectList) {
        this.ID = ID;
        this.name = name;
        this.info = info;
        this.batchList = batchList;
        this.subjectList = subjectList;
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

    public Vector<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(Vector<Batch> batchList) {
        this.batchList = batchList;
    }

    public Vector<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Vector<Subject> subjectList) {
        this.subjectList = subjectList;
    }  

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
