/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

/**
 *
 * @author Duy
 */
public class MyStyle {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth = screenSize.width;
    private static int screenHeight = screenSize.height;
    public static Font SplashLabelFont = new Font("Sans Serif", Font.PLAIN, 30);
    public static Font MediumTextFont = new Font("Sans Serif", Font.BOLD, screenHeight/50);
    public static Font SmallLabelFont = new Font("Sans Serif", Font.PLAIN, 10);
    public static Font MediumLabelFont = new Font("Sans Serif", Font.PLAIN, 25);
    public static Font BigLabelFont = new Font("Sans Serif", Font.PLAIN, 30);
}
