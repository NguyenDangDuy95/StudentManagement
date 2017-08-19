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
public class Department implements Serializable{
    private String ID;
    private String name;
    private Vector<Employee> empList;

    public Department() {
    }

    public Department(String ID, String name) {
        this.ID = ID;
        this.name = name;
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

    public Vector<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(Vector<Employee> empList) {
        this.empList = empList;
    }
    
    
}
