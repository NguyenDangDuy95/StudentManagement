/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Duy
 */
public class CustomUIManager{

    public static void customUI()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Tree.rowHeight", 3*MyConstants.LabelHeight/5);
            UIManager.put("Tree.drawDashedFocusIndicator", false);
            //UIManager.put("ProgressBarUI", new CircularProgressBarUI());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CustomUIManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
