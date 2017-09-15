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
import models.Score;

/**
 *
 * @author DUY
 */
public class ScoreDataService {
    public static Vector<Score> getScoreListByBatchID(String id) throws SQLException{
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getBatchScoreList(id));
        Vector<Score> sList = new Vector<>();
        while(rs.next()){
            Score sc = new Score(rs.getString("StudentID"), rs.getString("SubjectID"), rs.getInt("NumberOfExams"),String.valueOf(rs.getDouble("TheoryScore")), String.valueOf(rs.getDouble("PracticalScore")));
            sList.add(sc);
        }
        return sList;
    }
}
