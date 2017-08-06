/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import userControls.BackgroundImagePanel;

/**
 *
 * @author Duy
 */
public class SplashScreen extends JWindow{

    public SplashScreen() {
        BorderLayout bd = new BorderLayout();
        this.setLayout(bd);
        JFrame frame = new JFrame();
        frame.setLayout(bd);
        BackgroundImagePanel imageBackground = null;
        JLabel label = new JLabel("The Application is loading...", SwingConstants.CENTER);
        label.setFont(MyStyle.SplashLabelFont);
        try {
             imageBackground = new BackgroundImagePanel(getClass().getResource(MyConstants.SplashScreenImage).toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(imageBackground!= null)
        {
            imageBackground.add(label);
            this.add(imageBackground);
        }
        else
        {
            this.add(label,SwingConstants.CENTER);
        }
        
        this.setBackground(Color.yellow);
        this.setBounds((MyConstants.screenWidth-MyConstants.splashScreenWidth)/2,
                (MyConstants.screenHeight-MyConstants.splashScreenHeight)/2,
                MyConstants.splashScreenWidth,
                MyConstants.splashScreenHeight
        );
        
    }

    public void showSplash()
    {
        this.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        this.dispose();
        new LoginView().setVisible(true);
    }
    
    
}
