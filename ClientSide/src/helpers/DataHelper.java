/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Vector;

/**
 *
 * @author Duy
 */
public class DataHelper {
    private static Vector data;

    public DataHelper(Vector data) {
        this.data = data;
    }
    
    public void addRow(String attributes,Object o){
        Vector row = new Vector();
        row.addElement(attributes);
        row.addElement(o);
        data.add(row);
    }
    
    
}
