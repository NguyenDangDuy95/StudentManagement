/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import models.Attendance;
import models.Batch;
import models.Gender;
import models.Score;
import models.Student;

/**
 *
 * @author DUY
 */
public class BatchDataService {

    public static Vector<Batch> getBatchListByCourseID(String courseID) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getBatchListByCourseID(courseID));
        Vector<Batch> batchList = new Vector<>();
        batchList = createBatchListFromResultSet(rs);
        return batchList;
    }

    public static Vector<Batch> getFullBatchList() throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getFullBatchList());
        Vector<Batch> batchList = new Vector<>();
        batchList = createBatchListFromResultSet(rs);
        return batchList;
    }

    private static Vector<Batch> createBatchListFromResultSet(ResultSet rs) throws SQLException {
        Vector<Batch> batchList = new Vector<Batch>();
        while (rs.next()) {
            Batch batch = new Batch(
                    rs.getString("BatchID"),
                    rs.getString("BatchName"),
                    rs.getString("CourseID")
            );
            batch.setStdList(StudentDataService.GetStudentListByBatchID(batch.getId()));
            //
            ResultSet currentTeacher = DatabaseConnection.getExecutedResultSet(SQLHelper.getCurrentTeacher(batch.getId()));
            if (currentTeacher.next() == false) {
                System.out.println("No current Teacher");
            } else {
                do {
                    batch.setCurrentTeacher(EmployeeDataService.getEmployeeByID(currentTeacher.getString("EmployeeID")));
                } while (currentTeacher.next());
            }

            ResultSet currentSubject = DatabaseConnection.getExecutedResultSet(SQLHelper.getCurrentSubject(batch.getId()));
            if (currentSubject.next() == false) {
                System.out.println("No current Subject");
            } else {
                do {
                    batch.setCurrentSubject(SubjectDataService.getSubjectByID(currentSubject.getString("SubjectID")));
                } while (currentTeacher.next());
            }
            batch.setScList(ScoreDataService.getScoreListByBatchID(batch.getId()));
            batch.setAttList(getCurrentSubjectAttendance(batch));
            batch.setSbList(SubjectDataService.getSubjectListByBatchID(batch.getId()));
            batchList.addElement(batch);

        }
        return batchList;
    }

    private static Vector<Attendance> getCurrentSubjectAttendance(Batch batch) throws SQLException {
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getCurrentAttendance(batch.getId(), batch.getCurrentSubject().getID()));
        Vector<Attendance> attList = new Vector<>();
        if (rs.next() == false) {

        } else {
            do {
                Attendance att = new Attendance();
                att.setStudentID(rs.getString("StudentID"));
                att.setBatchID(batch.getId());
                att.setCourseID(rs.getString("CourseID"));
                att.setSubjectID(batch.getCurrentSubject().getID());
                String a = rs.getString("Statuss");
                if(rs.getString("Statuss")== null){
                    att.setStatus("");
                }else if(rs.getString("Statuss").equals("1")){
                    att.setStatus("X");
                }else att.setStatus("V");
                
                attList.add(att);
            } while (rs.next());
        }
        return attList;
    }

    public static void updateBatch(Batch batch, String type) throws SQLException {

        if (type.equals("attendance")) {
            for (Student std : batch.getStdList()) {
                DatabaseConnection.getUpdateResultSet(SQLHelper.deleteAttendance(std.getStudentID(), batch.getCurrentSubject().getID()));
            }
            for (Attendance att : batch.getAttList()) {
                DatabaseConnection.getUpdateResultSet(SQLHelper.insertAttendance(att));
            }
        } else {
            for (Score sc : batch.getScList()) {
                UpdateScore(sc);
            }
        }

    }

    public static void UpdateScore(Score sc) throws SQLException {
        CallableStatement csmt = DatabaseConnection.con.prepareCall("{call updateScore(?,?,?,?,?)}");
        csmt.setString(1, sc.getStudentID());
        csmt.setString(2, sc.getSubjectID());
        csmt.setInt(3, 1);
        if(sc.getTheoryScore()!= null){
            csmt.setDouble(4, Double.parseDouble(sc.getTheoryScore()));
        }else csmt.setNull(4, java.sql.Types.DECIMAL);
        if(sc.getActScore()!= null){
            csmt.setDouble(5, Double.parseDouble(sc.getActScore()));
        }else csmt.setNull(5, java.sql.Types.DECIMAL);
        csmt.execute();
    }
}
