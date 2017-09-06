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
public class Employee implements Serializable {

    private String EmployeeID;
    private String FirstName;
    private String LastName;
    private String PositionName;
    private String PayRate;
    private Gender Gender;
    private Date BirthDay;
    private String PersonalID;
    private String Email;
    private String PhoneNumber;
    private String Address;
    private String BirthPlace;
    private String FatherName;
    private String MotherName;
    private String FatherJob;
    private String MotherJob;
    private String ParentPhone;
    private Date StartDate;
    private Date EndDate;

    public Employee() {
    }

    public Employee(String EmployeeID, String FirstName, String LastName, String PositionName, String PayRate, Gender Gender, Date BirthDay, String PersonalID, String Email, String PhoneNumber, String Address, String BirthPlace, String FatherName, String MotherName, String FatherJob, String MotherJob, String ParentPhone, Date StartDate, Date EndDate) {
        this.EmployeeID = EmployeeID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PositionName = PositionName;
        this.PayRate = PayRate;
        this.Gender = Gender;
        this.BirthDay = BirthDay;
        this.PersonalID = PersonalID;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.BirthPlace = BirthPlace;
        this.FatherName = FatherName;
        this.MotherName = MotherName;
        this.FatherJob = FatherJob;
        this.MotherJob = MotherJob;
        this.ParentPhone = ParentPhone;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String PositionName) {
        this.PositionName = PositionName;
    }

    public String getPayRate() {
        return PayRate;
    }

    public void setPayRate(String PayRate) {
        this.PayRate = PayRate;
    }

    public Gender getGender() {
        return Gender;
    }

    public void setGender(Gender Gender) {
        this.Gender = Gender;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String BirthPlace) {
        this.BirthPlace = BirthPlace;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String MotherName) {
        this.MotherName = MotherName;
    }

    public String getFatherJob() {
        return FatherJob;
    }

    public void setFatherJob(String FatherJob) {
        this.FatherJob = FatherJob;
    }

    public String getMotherJob() {
        return MotherJob;
    }

    public void setMotherJob(String MotherJob) {
        this.MotherJob = MotherJob;
    }

    public String getParentPhone() {
        return ParentPhone;
    }

    public void setParentPhone(String ParentPhone) {
        this.ParentPhone = ParentPhone;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    @Override
    public String toString() {
        return this.FirstName+" "+ this.LastName;
    }
}
