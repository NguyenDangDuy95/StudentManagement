/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Duy
 */
public class BackgroundImagePanel extends JPanel{
    private Image backgroundImage;

    public BackgroundImagePanel(URI fileName) throws IOException{
        backgroundImage = ImageIO.read(new File(fileName));
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage( backgroundImage, 0, 0,MyConstants.SplashScreenWidth,MyConstants.SplashScreenHeight, this);
        
    }
}
