/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.AccessRole;
import models.Message;
import models.Request;
import services.VerificationService;
import test.ClientSideMain;
import userControls.*;
import views.base.BaseView;
import views.base.LoadingThread;

/**
 *
 * @author Duy
 */
public class LoginView extends JDialog implements BaseView {

    private JPanel container;
    private BackgroundImagePanel titlePanel;
    private JLabel lbTitle, lbUsername, lbPassword;
    private GradientPanel testTitle;
    private PlaceHolderTextField tfUsername;
    private PlaceholderPasswordField tfPassword;
    private CustomButton btnLogin;
    private CloseButton btnClose;
    private String username = "";
    private String password = "";
    private JCheckBox saveCredential;

    public LoginView() {
        ClientSideMain.CurrentState = "Login";
        System.out.println(MyConstants.LoginHeight);
        System.out.println(MyConstants.LoginWidth);
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        setBackground(Color.WHITE);
        setUndecorated(true);
        setBounds(MyConstants.LoginPositionX, MyConstants.LoginPositionY, MyConstants.LoginWidth, MyConstants.LoginHeight);
        setResizable(false);
        container = new JPanel();
        container.setLayout(null);
        container.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, MyStyle.DisableColor));
        container.setBackground(Color.white);
        container.setSize(MyConstants.LoginWidth, MyConstants.LoginHeight);
        setContentPane(container);
//        try {
//            titlePanel = new BackgroundImagePanel(getClass().getResource(MyConstants.LoginImage).toURI());
//        } catch (URISyntaxException | IOException ex) {
//            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
//        }
        lbTitle = new JLabel("Management", JLabel.CENTER);
        lbTitle.setForeground(Color.BLACK);
        lbTitle.setFont(MyStyle.BigLabelFont);

        testTitle = new GradientPanel(MyStyle.PrimaryColor, MyStyle.SecondaryColor);

        lbUsername = new JLabel("Username: ", JLabel.LEFT);
        lbUsername.setFont(MyStyle.MediumLabelFont);

        lbPassword = new JLabel("Password: ", JLabel.LEFT);
        lbPassword.setFont(MyStyle.MediumLabelFont);

        tfUsername = new PlaceHolderTextField();
        tfUsername.setPlaceholder("Input your username");
        tfUsername.setFont(MyStyle.MediumLabelFont);

        tfPassword = new PlaceholderPasswordField();
        tfPassword.setPlaceholder("Input your password");
        tfPassword.setFont(MyStyle.MediumLabelFont);

        btnLogin = new CustomButton("Login", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
        btnLogin.setFont(MyStyle.MediumLabelFont);

        btnClose = new CloseButton();
        btnClose.setWidthOfView(MyConstants.LoginWidth);
        btnClose.setOpaque(false);

        saveCredential = new CustomCheckBox("Save credential");
    }

    @Override
    public void initCommand() {
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkValidation()) {

                    System.out.println(ClientSideMain.CurrentUser.toString());
                    System.out.println(ClientSideMain.CurrentUserRole);
                    setVisible(false);
                    new MainView().setVisible(true);
                }
            }

        });
    }

    @Override
    public void draw() {
//        titlePanel.setLayout(null);
//        titlePanel.setBounds(0, 0, MyConstants.LoginWidth, MyConstants.LoginTitleHeight);

        testTitle.setLayout(null);
        testTitle.setBounds(0, 0, MyConstants.LoginWidth, MyConstants.LoginTitleHeight);

        lbTitle.setBounds((MyConstants.LoginWidth - MyConstants.LabelWidth*2) / 2,
                (MyConstants.LoginTitleHeight - MyConstants.LabelHeight) / 2,
                MyConstants.LabelWidth*2,
                MyConstants.LabelHeight
        );
        lbUsername.setBounds(
                MyConstants.SmallMargin,
                MyConstants.LoginTitleHeight + MyConstants.VerySmallMargin,
                MyConstants.LabelWidth,
                MyConstants.LabelHeight
        );

        tfUsername.setBounds(
                MyConstants.SmallMargin,
                lbUsername.getY() + MyConstants.LabelHeight + MyConstants.VerySmallMargin,
                MyConstants.TextFieldWidth,
                MyConstants.TextFieldHeight
        );
        lbPassword.setBounds(
                MyConstants.SmallMargin,
                tfUsername.getY() + MyConstants.TextFieldHeight + MyConstants.VerySmallMargin,
                MyConstants.LabelWidth,
                MyConstants.LabelHeight
        );

        tfPassword.setBounds(
                MyConstants.SmallMargin,
                lbPassword.getY() + MyConstants.LabelHeight + MyConstants.VerySmallMargin,
                MyConstants.TextFieldWidth,
                MyConstants.TextFieldHeight
        );

        saveCredential.setBounds(MyConstants.SmallMargin,
                tfPassword.getY() + MyConstants.TextFieldHeight + MyConstants.VerySmallMargin,
                MyConstants.LoginButtonWidth / 2 + MyConstants.VerySmallMargin,
                MyConstants.LabelHeight
        );

        btnLogin.setBounds(
                MyConstants.SmallMargin,
                saveCredential.getY() + MyConstants.LabelHeight + MyConstants.VerySmallMargin,
                MyConstants.LoginButtonWidth,
                MyConstants.LoginButtonHeight
        );
        btnLogin.setFocusPainted(false);

//        titlePanel.add(lbTitle);
//        titlePanel.add(btnClose);
        testTitle.add(lbTitle);
        testTitle.add(btnClose);
        //container.add(titlePanel);
        container.add(testTitle);
        container.add(lbUsername);
        container.add(lbPassword);
        container.add(tfUsername);
        container.add(tfPassword);
        container.add(saveCredential);
        container.add(btnLogin);
    }

    private boolean checkValidation() {
        username = "";
        username = tfUsername.getText();
        password = "";
        char[] ahihi = tfPassword.getPassword();
        for (int i = 0; i < tfPassword.getPassword().length; i++) {
            password += tfPassword.getPassword()[i];
        }
        if (username.isEmpty() || username.equals("")) {
            Control.createCustomDialog(MyConstants.OptionDialogType.Message, MyConstants.WarningMessage, MyConstants.EmptyUsername);
            return false;
        } else if (password.isEmpty()) {
            Control.createCustomDialog(MyConstants.OptionDialogType.Message, MyConstants.WarningMessage, MyConstants.EmptyPassword);
            return false;
        } else {
            //send request to server
            //return a string true or false from server, check and return for validate
            Message mgs = new Message();
            mgs = VerificationService.verify(username, password);
            if (mgs != null) {

                String result = mgs.getTitle();
                if (result.equals(Request.FailedMessage)) {
                    if (mgs.getBody().equals(Request.Username)) {
                        Control.createCustomDialog(MyConstants.OptionDialogType.Message, MyConstants.WarningMessage, MyConstants.WrongUsername);
                        return false;
                    } else {
                        Control.createCustomDialog(MyConstants.OptionDialogType.Message, MyConstants.WarningMessage, MyConstants.WrongPassword);
                        return false;
                    }
                } else {
                    if (mgs.getBody().equals(Request.StudentObject)) {
                        ClientSideMain.CurrentUser = mgs.getStudent();
                        ClientSideMain.CurrentUserRole = Request.StudentObject;
                    }
                    if (mgs.getBody().equals(Request.EmployeeObject)) {
                        ClientSideMain.CurrentUser = mgs.getEmployee();
                        ClientSideMain.CurrentUserRole = Request.EmployeeObject;
                    }
                    if (mgs.getBody().equals(Request.AdminObject)) {
                        ClientSideMain.CurrentUser = mgs.getEmployee();
                        ClientSideMain.CurrentUserRole = Request.AdminObject;
                    }
                    return true;
                }
            } else {
                Control.createCustomDialog(MyConstants.OptionDialogType.Message, MyConstants.WarningMessage, MyConstants.VerificationError);
                return false;
            }
        }
    }
}
