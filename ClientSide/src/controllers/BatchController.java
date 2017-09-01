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
import models.Message;
import models.Request;


/**
 *
 * @author Duy
 */
public class BatchController {
    private static BatchController _instance = null;
    
    private Vector<Batch> _batchList;
    
    public static BatchController getInstance(){
        if(_instance == null){
            _instance = new BatchController();
        }
        return _instance;
    }
    
    private BatchController(){
        _batchList = new Vector<>();
    }
    
    public DefaultTableModel getTableModel(){
//        Vector header = new Vector();
//        header.add(Batch.COLUMN_ID);
//        header.add(Employee.COLUMN_NAME);
//        header.add(Employee.COLUMN_AGE);
//        header.add(Employee.COLUMN_DEPARTMENT);
//        
//        Vector data = new Vector();
//        for(int i = 0; i < _batchList.size(); i++){
//            Employee employee = _batchList.get(i);
//            Vector row = new Vector();
//            row.addElement(employee.getId());
//            row.addElement(employee.getName());
//            row.addElement(employee.getAge());
//            row.addElement(employee.getDepartment().getName());
//            data.addElement(row);
//        }
//        
//        DefaultTableModel dataModel = new DefaultTableModel(data, header){
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }            
//        };        
//        return dataModel;
        return null;
    }
    
    public void load(){
        //request to server to get Batch list       
        try {
            ServerConnection.oos.writeObject(new Message(Request.GetBatchList));
            ServerConnection.oos.flush();
            _batchList = (Vector<Batch>) ServerConnection.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            
        }
    }
    
    public Vector<Batch> get(){
        if(_batchList.size() == 0){
            load();
        }
        return _batchList;
    }
    
    public Batch getBatchByID(String id){
        for(Batch batch : _batchList)
        {
            if(batch.getId().equals(id)) return batch;
        }
        return null;
    }
    public void add(Batch batch){
        //request server to save to database

        Message mgs = new Message();
        mgs.setTitle(Request.AddMessage);
        mgs.setBody(Request.BatchObject);
        mgs.setID(batch.getId());
        mgs.setBat(batch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
            
        }
        //save temp to list
        _batchList.addElement(batch);
    }
    
    public void update(Batch batch){
        //request server to update to database
        Message mgs = new Message();
        mgs.setTitle(Request.UpdateMessage);
        mgs.setBody(Request.BatchObject);
        mgs.setBat(batch);
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
          
        }

        //update to list AUTO
        for(Batch b : _batchList)
        {
            if(b.getId().equals(batch.getId())){ b = batch; break;}
        }
    }
    
    public void delete(Batch batch){
        //request server to delete from database
        Message mgs = new Message(Request.DeleteMessage,Request.BatchObject,batch.getId());
        try {
            ServerConnection.oos.writeObject(mgs);
            ServerConnection.oos.flush();
        } catch (IOException ex) {
     
        }

        //delete from list
        _batchList.removeElement(batch);
    }
    
}
