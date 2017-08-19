/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Vector;



/**
 *
 * @author Duy
 */
public interface IDataStore {

    Vector GetItems();
    boolean UpdateItemByID(String id);
    boolean DeleteItemByID(String id);
}
