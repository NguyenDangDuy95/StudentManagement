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
public class Student implements Serializable {

    private String StudentID;
    private String FirstName;
    private String LastName;
    private String Course;
    private String Batch;
    private Gender Gender;
    private Date BirthDay;
    private String Address;
    private String BirthPlace;
    private String PersonalID;
    private String PhoneNumber;
    private String Email;
    private String FatherName;
    private String FatherJob;
    private String MotherName;
    private String MotherJob;
    private String ParentPhone;
    private String Schoolarship;
    private Date StartDate;
    private Date EndDate;

    public Student() {
    }

    public Student(String StudentID, String FirstName, String LastName, String Course, String Batch, Gender Gender, Date BirthDay, String Address, String BirthPlace, String PersonalID, String PhoneNumber, String Email, String FatherName, String FatherJob, String MotherName, String MotherJob, String ParentPhone, String Schoolarship, Date StartDate, Date EndDate) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Course = Course;
        this.Batch = Batch;
        this.Gender = Gender;
        this.BirthDay = BirthDay;
        this.Address = Address;
        this.BirthPlace = BirthPlace;
        this.PersonalID = PersonalID;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.FatherName = FatherName;
        this.FatherJob = FatherJob;
        this.MotherName = MotherName;
        this.MotherJob = MotherJob;
        this.ParentPhone = ParentPhone;
        this.Schoolarship = Schoolarship;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }
    
    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
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

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
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

    public String getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(String PersonalID) {
        this.PersonalID = PersonalID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public String getFatherJob() {
        return FatherJob;
    }

    public void setFatherJob(String FatherJob) {
        this.FatherJob = FatherJob;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String MotherName) {
        this.MotherName = MotherName;
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

    public String getSchoolarship() {
        return Schoolarship;
    }

    public void setSchoolarship(String Schoolarship) {
        this.Schoolarship = Schoolarship;
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
        return this.FirstName + this.LastName;
    }
    
    
}
