/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.MyConstants;
import helpers.MyStyle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import userControls.BackgroundImagePanel;

/**
 *
 * @author Duy
 */
public class SplashScreen extends JWindow{
    private URI splashImage;
    private JLabel label;
    private BackgroundImagePanel imagePanel;
    public SplashScreen() {
        setLayout(null);
        
        setBounds((MyConstants.ScreenWidth-MyConstants.SplashScreenWidth)/2,
                (MyConstants.ScreenHeight-MyConstants.SplashScreenHeight)/2,
                MyConstants.SplashScreenWidth,
                MyConstants.SplashScreenHeight
        );
        try {
            
            splashImage = getClass().getResource(MyConstants.SplashScreenImage).toURI();
            imagePanel = new BackgroundImagePanel(splashImage);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        label = new JLabel("The Application is loading...", SwingConstants.CENTER);
        label.setFont(MyStyle.SplashLabelFont);
        label.setBounds(0, 0, MyConstants.SplashScreenWidth, MyConstants.LabelHeight);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        imagePanel.setLayout(null);
        imagePanel.setBounds(0,
                0,
                MyConstants.SplashScreenWidth,
                MyConstants.SplashScreenHeight
        );
        imagePanel.add(label);
        add(imagePanel);
    }

    public void showSplash()
    {
        this.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        this.dispose();
        new LoginView().setVisible(true);
    }
    
    
}
