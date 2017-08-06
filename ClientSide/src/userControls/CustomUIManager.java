/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Duy
 */
public class CustomUIManager{

    public CustomUIManager() {
        customUI();
    }
    private void customUI()
    {
        UIManager.put("OptionPane.minimumSize",new Dimension(MyConstants.screenWidth/5,MyConstants.screenHeight/8));
        UIManager.put("OptionPane.messageFont", MyStyle.MediumLabelFont);
        UIManager.put("OptionPane.buttonOrientation", SwingConstants.RIGHT);
        UIManager.getLookAndFeelDefaults().put("OptionPane.sameSizeButtons", true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CustomUIManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
