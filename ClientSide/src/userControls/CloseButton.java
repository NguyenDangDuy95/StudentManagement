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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import views.LoginView;

/**
 *
 * @author Duy
 */
public class CloseButton extends JButton{
    private Image closeIcon = null;
    public CloseButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        try {
            closeIcon = ImageIO.read(getClass().getResource(MyConstants.CloseImage));
        } catch (IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        setIcon(new ImageIcon(closeIcon));
        setOpaque(true);
        //setBounds(MyConstants.LoginWidth - 100, 0, 100, 50);
        setBackground(MyStyle.Transparent);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Control.createCustomDialog(MyConstants.OptionDialogType.Confirm, MyConstants.ExitTitle, MyConstants.QuitMessage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setOpaque(true);
                setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
            }
        });
    }
    
    public void setWidthOfView(int width)
    {
        setBounds(width - 100, 0, 100, 50);
    }
    
    
}
