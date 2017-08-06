/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Duy
 */
public class MyConstants {

    public static String SplashScreenImage = "/images/SplashScreen.jpg";
    public static String LoginTitleBackgroundImage = "/images/LoginTitleImage.jpg";
    public static String LoginImage = "/images/LoginImage.jpg";
    //Margin
    public static int verySmallMargin = 50;
    public static int smallMargin = 75;
    public static int mediumMargin = 100;
    public static int largeMargin = 150;
    public static int veryLargeMargin = 200;

    //
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = screenSize.width;
    public static int screenHeight = screenSize.height;

    public static int splashScreenWidth = screenWidth / 3;
    public static int splashScreenHeight = screenHeight / 3;

    public static int loginWidth = screenWidth / 3;
    public static int loginHeight = screenHeight / 3;
    public static int loginTitleHeight = loginHeight / 3;
    public static int loginInputContentHeight = loginTitleHeight * 2;

    public static int loginPositionX = (screenWidth - loginWidth) / 2;
    public static int loginPositionY = (screenHeight - loginHeight) / 2;

    //loginView
    public static int itemSize = loginInputContentHeight / 9;
    public static int doubleItemSize = itemSize * 2;
    public static int tripItemSize = itemSize * 3;
    public static int quadItemSize = itemSize * 4;

    //loginMessage
    public static String emptyUsername= "You have to input your Username to login";
    public static JLabel emptyUserLabel = new JLabel(emptyUsername, SwingConstants.CENTER);
    public static String emptyPassword= "You have to input your Password to login";
    public static String warningMessage = "Warning";
    
}
