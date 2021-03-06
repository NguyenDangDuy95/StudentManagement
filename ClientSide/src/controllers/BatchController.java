/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.ServerConnection;
import java.io.IOException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.Batch;
import models.Course;
import models.Message;
import models.Request;

/**
 *
 * @author Duy
 */
public class BatchController {

    private static BatchController _instance = null;

    private Vector<Batch> _batchList;

    public static BatchController getInstance() {
        if (_instance == null) {
            _instance = new BatchController();
        }
        return _instance;
    }

    private BatchController() {
        _batchList = new Vector<>();
    }

    public DefaultTableModel getTableModel() {
        return null;
    }

    public DefaultTableModel getTableModelFromList(Vector<Batch> batchList) {
        Vector header = new Vector();
        header.add("Batch name");
        header.add("Course name");
        header.add("Number of student");
        Vector data = new Vector();
        for (Batch batch : batchList) {
            Vector row = new Vector();
            row.addElement(batch.getName());
            row.addElement(CourseController.getInstance().getCourseByID(batch.getCourseID()).getName());
            row.addElement(batch.getStdList().size());
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    public void load() {
        //request to server to get Batch list       
        try {
            ServerConnection.oos.writeObject(new Message(Request.GetBatchList));
            System.out.println(Request.GetBatchList);
            ServerConnection.oos.flush();
            _batchList = (Vector<Batch>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {

        }
    }

    public Batch getBatchByID(String id) {
        for (Batch batch : _batchList) {
            if (batch.getId().equals(id)) {
                return batch;
            }
        }
        return null;
    }

    public Vector<Batch> get() {
        if (_batchList.size() == 0) {
            load();
        }
        return _batchList;
    }

    public void add(Course course) {
        //request server to save to database
        Batch lastestBatch = _batchList.get(_batchList.size()-1);
        String id = lastestBatch.getId().trim();
        int numberOfBatch = Integer.parseInt(id.substring(1));
        Batch newBatch = new Batch("B"+numberOfBatch, "Batch "+numberOfBatch, course.getID());
        
        Message mgs = new Message();
        mgs.setTitle(Request.AddMessage);
        mgs.setBody(Request.BatchObject);
        mgs.setBatch(newBatch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }
        //save temp to list
        _batchList.addElement(newBatch);
    }

    public void update(Batch batch) {
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(Request.BatchObject);
        mgs.setBatch(batch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //update to list AUTO
        for (Batch b : _batchList) {
            if (b.getId().equals(batch.getId())) {
                b = batch;
                break;
            }
        }
    }

    public void update(Batch batch,String target) {
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(target);
        mgs.setBatch(batch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //update to list AUTO
        for (Batch b : _batchList) {
            if (b.getId().equals(batch.getId())) {
                b = batch;
                break;
            }
        }
    }
    
    public void delete(Batch batch) {
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage, Request.BatchObject);
        mgs.setBatch(batch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {

        }

        //delete from list
        _batchList.removeElement(batch);
    }

}
