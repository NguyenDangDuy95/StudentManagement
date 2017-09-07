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
                    rsSubject.getString("CourseID")
            );
            subList.addElement(sub);
        }
        return subList;
    }
}
