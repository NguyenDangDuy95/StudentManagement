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
 * @author Duy
 */
public class Employee implements Serializable{
    private String ID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Date birthDay;
    private Gender gender;
    private TypeOfEmployee typeOfEmployee;
    private String departmentID;
    private String salaryRateID;
    private Date startDay;
    private Date endDay;
    private String fatherName;
    private String motherName;
    private String fatherJob;
    private String motherJob;
    private String parentPhone;
    private AccessRole accessRole;

    public Employee() {
    }

    public Employee(String ID, String firstName, String lastName, String phone, String address, Date birthDay, Gender gender, TypeOfEmployee typeOfEmployee, String departmentID, String salaryRateID, Date startDay, Date endDay, String fatherName, String motherName, String fatherJob, String motherJob, String parentPhone, AccessRole accessRole) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.birthDay = birthDay;
        this.gender = gender;
        this.typeOfEmployee = typeOfEmployee;
        this.departmentID = departmentID;
        this.salaryRateID = salaryRateID;
        this.startDay = startDay;
        this.endDay = endDay;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherJob = fatherJob;
        this.motherJob = motherJob;
        this.parentPhone = parentPhone;
        this.accessRole = accessRole;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public TypeOfEmployee getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(TypeOfEmployee typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getSalaryRateID() {
        return salaryRateID;
    }

    public void setSalaryRateID(String salaryRateID) {
        this.salaryRateID = salaryRateID;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherJob() {
        return fatherJob;
    }

    public void setFatherJob(String fatherJob) {
        this.fatherJob = fatherJob;
    }

    public String getMotherJob() {
        return motherJob;
    }

    public void setMotherJob(String motherJob) {
        this.motherJob = motherJob;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public AccessRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(AccessRole accessRole) {
        this.accessRole = accessRole;
    }
    
}
