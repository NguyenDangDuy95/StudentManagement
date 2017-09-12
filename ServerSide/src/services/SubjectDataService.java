/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import models.Subject;
import models.SubjectBatch;

/**
 *
 * @author DUY
 */
public class SubjectDataService {

    public static Vector<Subject> getSubjectListByCourseID(String courseID) throws SQLException {
        ResultSet rsSubject = DatabaseConnection.getExecutedResultSet(SQLHelper.getSubjectListByCourseID(courseID));
        Vector<Subject> subList = new Vector<>();
        while (rsSubject.next()) {
            Subject sub = new Subject(
                    rsSubject.getString("SubjectID"),
                    rsSubject.getString("SubjectName"),
                    rsSubject.getString("NumberOfLessons"),
                    rsSubject.getString("SubjectInformation"),
                    rsSubject.getString("semester"),
                    rsSubject.getString("CourseID"),
                    rsSubject.getString("SubjectCode")
            );
            subList.addElement(sub);
        }
        return subList;
    }
    public static Subject getSubjectByID(String id) throws SQLException{
        ResultSet rsSubject = DatabaseConnection.getExecutedResultSet(SQLHelper.getSubjectByID(id));
        Subject sub = new Subject();
        if(rsSubject.next()){
            sub.setID(rsSubject.getString("SubjectID"));
            sub.setName(rsSubject.getString("SubjectName"));
            sub.setCourseID(rsSubject.getString("CourseID"));
            sub.setNumberOfLesson(rsSubject.getString("NumberOfLessons"));
            sub.setSemester(rsSubject.getString("semester"));
            sub.setSubjectInfo(rsSubject.getString("SubjectInformation"));
            sub.setSubjectCode(rsSubject.getString("SubjectCode"));
        }
        return sub;
    }
    public static Vector<SubjectBatch> getSubjectListByBatchID(String id) throws SQLException{
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getSubjectListByBatchID(id));
        Vector<SubjectBatch> subList = new Vector<>();
        if(rs.next()==false){
        
        }else{
            do{
                SubjectBatch sb = new SubjectBatch(
                        EmployeeDataService.getEmployeeByID(rs.getString("EmployeeID")),
                        getSubjectByID(rs.getString("SubjectID")),
                        rs.getString("BatchID"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate")
                );
                subList.add(sb);
            }while(rs.next());
        }
        return subList;
    }
}
