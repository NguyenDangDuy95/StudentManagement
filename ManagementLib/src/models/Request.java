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
public class Request implements Serializable{
    public static String GetStudentList = "studentlist";
    public static String GetCourseList = "courselist";
    public static String GetEmployeeList = "employeelist";
    public static String UpdateStudent = "updatestudent";
    public static String UpdateEmployee = "updateemployee";
    public static String UpdateCourse = "updatecourse";
    public static String Verification = "verify";
}
