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
        System.out.println(rsCourse);
        while (rsCourse.next()) {
            Course course = new Course();
            course.setID(rsCourse.getString("CourseID"));
            course.setName(rsCourse.getString("CourseName"));
            course.setInfo(rsCourse.getString("CourseInformation"));
            course.setBatchList(BatchDataService.getBatchListByCourseID(course.getID()));
            course.setSubjectList(SubjectDataService.getSubjectListByCourseID(course.getID()));
            courseList.add(course);
        }
        return courseList;
    }
}
