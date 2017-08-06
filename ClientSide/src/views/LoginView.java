/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import userControls.*;
import views.base.BaseView;

/**
 *
 * @author Duy
 */
public class LoginView extends JDialog implements BaseView {

    private JPanel container;
    private BackgroundImagePanel titlePanel;
    private JLabel lbTitle, lbUsername, lbPassword;
    private PlaceholderTextField tfUsername;
    private PlaceholderPasswordField tfPassword;
    private JButton btnLogin, btnCancel;
    private String username = "";
    private String password = "";

    public LoginView() {
        System.out.println(MyConstants.loginHeight);
        System.out.println(MyConstants.loginWidth);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Do you want to quit", null, JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            }
            
        });
        
        setResizable(false);
        this.setBounds(MyConstants.loginPositionX, MyConstants.loginPositionY, MyConstants.loginWidth, MyConstants.loginHeight);
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        container = new JPanel();
        container.setLayout(null);
        //container.setBackground(Color.white);
        container.setSize(MyConstants.loginWidth, MyConstants.loginHeight);
        setContentPane(container);
        try {
            titlePanel = new BackgroundImagePanel(getClass().getResource(MyConstants.LoginImage).toURI());
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbTitle = new JLabel("Login",JLabel.LEFT);
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setFont(MyStyle.SplashLabelFont);

        lbUsername = new JLabel("Username: ",JLabel.LEFT);
        lbUsername.setFont(MyStyle.MediumLabelFont);

        lbPassword = new JLabel("Password: ",JLabel.LEFT);
        lbPassword.setFont(MyStyle.MediumLabelFont);

        tfUsername = new PlaceholderTextField();
        tfUsername.setPlaceholder("Input your username");
        tfUsername.setFont(MyStyle.MediumLabelFont);

        tfPassword = new PlaceholderPasswordField();
        tfPassword.setPlaceholder("Input your password");
        tfPassword.setFont(MyStyle.MediumLabelFont);

        btnLogin = new JButton("Login");
        btnLogin.setFont(MyStyle.MediumLabelFont);
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(MyStyle.MediumLabelFont);
    }

    @Override
    public void initCommand() {
        btnLogin.addActionListener((ActionEvent e) -> {
            checkValidation();
        });
        btnCancel.addActionListener((ActionEvent e) -> {
            int result = JOptionPane.showConfirmDialog(this, "Do you want to quit", null, JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });
    }

    @Override
    public void draw() {
        titlePanel.setLayout(null);
        titlePanel.setBounds(0, 0, MyConstants.loginWidth, MyConstants.loginTitleHeight);
        lbTitle.setBounds(MyConstants.itemSize, (MyConstants.loginTitleHeight - MyConstants.itemSize) / 2, MyConstants.tripItemSize, MyConstants.itemSize);

        lbUsername.setBounds(MyConstants.doubleItemSize, MyConstants.itemSize * 6, MyConstants.tripItemSize, MyConstants.itemSize);
        lbPassword.setBounds(MyConstants.doubleItemSize, MyConstants.itemSize * 8, MyConstants.tripItemSize, MyConstants.itemSize);

        tfUsername.setBounds(MyConstants.itemSize * 6, MyConstants.itemSize * 6, MyConstants.loginWidth - MyConstants.quadItemSize * 2, MyConstants.itemSize);
        tfPassword.setBounds(MyConstants.itemSize * 6, MyConstants.itemSize * 8, MyConstants.loginWidth - MyConstants.quadItemSize * 2, MyConstants.itemSize);

        btnCancel.setBounds(MyConstants.loginWidth - MyConstants.itemSize * 5, MyConstants.itemSize * 10, MyConstants.tripItemSize, MyConstants.itemSize);
        btnLogin.setBounds(MyConstants.loginWidth - MyConstants.itemSize * 9, MyConstants.itemSize * 10, MyConstants.tripItemSize, MyConstants.itemSize);

        titlePanel.add(lbTitle);
        container.add(titlePanel);
        container.add(lbUsername);
        container.add(lbPassword);
        container.add(tfUsername);
        container.add(tfPassword);
        container.add(btnLogin);
        container.add(btnCancel);
    }

    private void checkValidation() {
        username = tfUsername.getText();
        for (int i = 0; i < tfPassword.getPassword().length; i++) {
            password += tfPassword.getPassword()[i];
        }
        if(username.isEmpty())
        {
            JOptionPane.showMessageDialog(this, MyConstants.emptyUsername, MyConstants.warningMessage, JOptionPane.PLAIN_MESSAGE);
        }
        else if(password.isEmpty())
        {
            JOptionPane.showMessageDialog(this, MyConstants.emptyPassword, MyConstants.warningMessage, JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            
            setVisible(false);
            System.out.printf("Username: %s \n Password: %s", username, password);
        }
        
    }
}
