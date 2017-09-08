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
    public static Vector<Batch> getFullBatchList() throws SQLException{
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getFullBatchList());
        Vector<Batch> batchList = new Vector<>();
        batchList = createBatchListFromResultSet(rs);
        return batchList;
    }
    private static Vector<Batch> createBatchListFromResultSet(ResultSet rs) throws SQLException{
        Vector<Batch> batchList = new Vector<Batch>();
        while (rs.next()) {
            Batch batch = new Batch(
                    rs.getString("BatchID"),
                    rs.getString("BatchName"),
                    rs.getString("CourseID")
            );
            batch.setStdList(StudentDataService.GetStudentListByBatchID(batch.getId()));
            batchList.addElement(batch);
        }
        return batchList;
    }
}
