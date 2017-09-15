/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Duy
 */
public class MyConstants {


    public static String SERVERIP = "192.168.1.17";

    public static int PORT = 3002;
    
    public static String AppTitle = "School Management";
    
    public static String SplashScreenImage = "/images/SplashScreen.jpg";
    public static String LoginTitleBackgroundImage = "/images/LoginTitleImage.jpg";
    public static String LoginImage = "/images/LoginTitleImageOrange.jpg";
    public static String CloseImage = "/images/CloseIcon.png";
    public static String CheckedImage = "/images/Checked.png";
    public static String UnCheckedImage = "/images/Unchecked.png";
    public static String PressedImage = "/images/PressedIcon.png";
    public static String DisableSelectedImage = "/images/DisabledSelectedIcon.png";
    //
    public static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int ScreenWidth = ScreenSize.width;
    public static int ScreenHeight = ScreenSize.height;

    public static int SplashScreenWidth = ScreenWidth / 3;
    public static int SplashScreenHeight = ScreenHeight / 3;

    public static int LoginWidth = ScreenWidth / 5;
    public static int LoginHeight = ScreenHeight / 2;
    public static int LoginTitleHeight = LoginHeight / 4;
    public static int LoginInputContentHeight = LoginTitleHeight * 3;

    public static int LoginPositionX = (ScreenWidth - LoginWidth) / 2;
    public static int LoginPositionY = (ScreenHeight - LoginHeight) / 2;

    //loginView
    public static int VerySmallMargin = ScreenHeight / 72;
    public static int SmallMargin = VerySmallMargin * 2;
    public static int MediumMargin = SmallMargin * 2;
    public static int LargeMargin = MediumMargin * 2;

    public static int LabelHeight = ScreenHeight / 36;
    public static int LabelWidth = LabelHeight * 4;
    
    public static int MainPanelButtonWidth = SmallMargin*5;
    public static int MainPanelButtonHeight = VerySmallMargin*3;

    public static int TextFieldHeight = ScreenHeight / 18;
    public static int TextFieldWidth = LoginWidth - 2 * SmallMargin;

    public static int LoginButtonHeight = ScreenHeight / 18;
    public static int LoginButtonWidth = LoginWidth - 2 * SmallMargin;

    public static int OptionPaneWidth = LoginWidth;
    public static int OptionPaneHeight = LoginHeight / 3;
    public static int OptionPaneX = (ScreenWidth - OptionPaneWidth) / 2;
    public static int OPtionPaneY = (ScreenHeight - OptionPaneHeight) / 2;
    public static int OptionPaneButtonWidth = OptionPaneWidth / 5;
    public static int OptionPaneButtonHeight = LabelHeight;

    //mainview
    public static int ToolbarHeight = ScreenHeight / 36;
    public static int ToolbarTitleWidth = ScreenWidth/9;
    public static int LeftPanelWidth = ScreenWidth / 6;
    public static int LeftPanelHeight = ScreenHeight - ToolbarHeight - SmallMargin;

    public static int InfoPanelWidth = ScreenWidth / 4;
    public static int InfoPanelHEight = LeftPanelHeight * 2 / 3 - SmallMargin;

    public static int MainPanelWidth = ScreenWidth - InfoPanelWidth - LeftPanelWidth - MediumMargin;
    public static int MainPanelHeight = LeftPanelHeight * 2 / 3 - SmallMargin;

    public static int BottonPanelWidth = ScreenWidth - VerySmallMargin * 3 - LeftPanelWidth;
    public static int BottomPanelHeight = LeftPanelHeight / 3 + VerySmallMargin;
    //
    
    //tree view
    public static String FirstSemester = "Semester I";
    public static String SecondSemester = "Semester II";
    public static String ThirdSemester = "Semester III";
    public static String FourthSemester = "Semester IV";
    public static String AdminRole = "Adminstrator";
    public static String TeacherRole = "Teacher";
    //
    
    //loginMessage
    public static String EmptyUsername = "Empty Username";
    public static String WrongUsername = "Wrong Username";
    public static String EmptyPassword = "Empty Password";
    public static String WrongPassword = "Wrong Password";
    public static String VerificationError = "Verification Error";
    public static String QuitMessage = "Do you want to quit";
    public static String WarningMessage = "Warning";
    public static String ExitTitle = "Exit";
    public static String YesText = "Yes";
    public static String NoText = "No";
    public static String OKText = "OK";

    public static enum OptionDialogType {
        Confirm,
        Message
    }

}
