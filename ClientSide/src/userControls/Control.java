/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import helpers.MyStyle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author Duy
 */
public class Control {
    public static boolean rs = false;
    public static JLabel createCenterLabel(String content)
    {
        JLabel lb = new JLabel(content,JLabel.CENTER);
        lb.setFont(MyStyle.MediumLabelFont);
        return lb;
    }
    public static void createCustomDialog(MyConstants.OptionDialogType type,String title,String message)
    {
        //int result = JOptionPane.showConfirmDialog(null, createCenterLabel(MyConstants.QuitMessage), null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        CustomOptionDialog ahihi = new CustomOptionDialog(type, title, message);
        ahihi.addPropertyChangeListener("isClicked", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (type == MyConstants.OptionDialogType.Message) {
                    return;
                }
                System.exit(0);
            }
        });
    }
    public static boolean getResult(MyConstants.OptionDialogType type,String title,String message){
        CustomOptionDialog ahihi = new CustomOptionDialog(type, title, message);
        ahihi.addPropertyChangeListener("isClicked", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (type == MyConstants.OptionDialogType.Message) {
                    return;
                }
                rs = true;
            }
        });
        return rs;
    }
}
