/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

/**
 *
 * @author Duy
 */
public class CustomCheckBox extends JCheckBox{
    private Image selectedIcon,disabledIcon,pressedIcon,disabledSelectedIcon;
    public CustomCheckBox(String text) {
        setOpaque(true);
        try {
            selectedIcon= ImageIO.read(getClass().getResource(MyConstants.CheckedImage));
            disabledIcon= ImageIO.read(getClass().getResource(MyConstants.UnCheckedImage));
            pressedIcon= ImageIO.read(getClass().getResource(MyConstants.PressedImage));
            disabledSelectedIcon= ImageIO.read(getClass().getResource(MyConstants.DisableSelectedImage));
        } catch (IOException ex) {
            Logger.getLogger(CustomCheckBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        setText(text);
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setBackground(Color.WHITE);
        setFont(MyStyle.MediumLabelFont);
        setIcon(new ImageIcon(disabledIcon));
        setSelected(false);
        setSelectedIcon(new ImageIcon(selectedIcon));
        setDisabledIcon(new ImageIcon(disabledIcon));
        setPressedIcon(new ImageIcon(disabledSelectedIcon));
        setDisabledSelectedIcon(new ImageIcon(disabledSelectedIcon));
    }
    
}
