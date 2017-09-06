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
import models.Batch;
import models.Course;
import models.Subject;

/**
 *
 * @author Duy
 */
public class CourseDataService {

    public static Vector<Course> getCourseList() throws SQLException {
        Vector<Course> courseList = new Vector<>();
        ResultSet rsCourse = DatabaseConnection.getExecutedResultSet(SQLHelper.getCourseList());

        while (rsCourse.next()) {
            Course course = new Course();
            course.setID(rsCourse.getString(1));
            course.setName(rsCourse.getString(2));
            ResultSet rsSubject = DatabaseConnection.getExecutedResultSet(SQLHelper.getSubjectListByCourseID(course.getID()));
            ResultSet rsBatch = DatabaseConnection.getExecutedResultSet(SQLHelper.getBatchListByCourseID(course.getID()));
            
            Vector<Batch> batchList = new Vector<Batch>();
            while (rsBatch.next()) {
                Batch batch = new Batch(rsBatch.getString(1), rsBatch.getString(2), rsBatch.getString(3));
                batch.setStdList(StudentDataService.GetStudentListByBatchID(batch.getId()));
                batchList.addElement(batch);
            }
            course.setBatchList(batchList);
            Vector<Subject> subList = new Vector<>();
            while (rsSubject.next()) {
                Subject sub = new Subject(
                        rsSubject.getString("SubjectID"),
                        rsSubject.getString("SubjectName"),
                        rsSubject.getString("NumberOfLessons"),
                        rsSubject.getString("CourseInformation"),
                        rsSubject.getString("semester"),
                        rsSubject.getString("CourseID")
                );
                subList.addElement(sub);
            }
            course.setSubjectList(subList);
            courseList.addElement(course);
        }
        return courseList;
    }
}
