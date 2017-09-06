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
    //lohin
    public static String Verification = "verify";
    //
    public static String GetStudentList = "studentlist";
    public static String GetStudentByID = "student";
    public static String GetStudentListByBatchID = "studentlistbybatchid";
    public static String GetCourseList = "courselist";
    
    public static String GetEmployeeList = "employeelist";
    public static String GetEmployeeByID = "employee";
    
    public static String GetBatchList = "batchlist";
    public static String GetBatchListByCourseID = "batchlistbycourseid";
    public static String GetBatchByID = "batch";
    
    public static String StudentObject = "student";
    public static String EmployeeObject = "employee";
    public static String BatchObject = "batch";
    public static String CourseObject = "course";
    public static String SubjectObject = "subject";
    
    public static String AddMessage = "add";
    public static String DeleteMessage = "delete";
    public static String UpdateMessage = "update";
}
