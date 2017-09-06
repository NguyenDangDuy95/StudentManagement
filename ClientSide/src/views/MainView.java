/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.BatchController;
import controllers.CourseController;
import controllers.EmployeeController;
import controllers.StudentController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Employee;
import test.ClientSideMain;
import userControls.CloseButton;
import userControls.CustomTreeView;
import views.base.BaseView;

/**
 *
 * @author Duy
 */
public class MainView extends JFrame implements BaseView {
    public static Object CurrentUser;
    private JPanel container, leftPanel, mainPanel, infoPanel, bottomPanel, toolbarPanel,userPanel;
    private CloseButton closeButton;
    private JLabel usernameLabel;
    private CustomTreeView customTreeView;

    public MainView() throws HeadlessException {
        ClientSideMain.CurrentState = "MainView";
        CourseController.getInstance().load();
        BatchController.getInstance().load();
        EmployeeController.getInstance().load();
        StudentController.getInstance().load();
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        container = new JPanel();
        leftPanel = new JPanel();
        mainPanel = new JPanel();
        infoPanel = new JPanel();
        bottomPanel = new JPanel();
        toolbarPanel = new JPanel();
        userPanel = new JPanel();
        
        usernameLabel = new JLabel();
        
        closeButton = new CloseButton();
        
        customTreeView = new CustomTreeView();
    }

    @Override
    public void draw() {
        setBounds(0, 0, MyConstants.ScreenWidth, MyConstants.ScreenHeight);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        container.setBounds(0, 0, MyConstants.ScreenWidth, MyConstants.ScreenHeight);
        container.setLayout(null);
        container.setOpaque(true);
        container.setBackground(MyStyle.BackgroundColor);
        
        /* toolbar
        *  include close button, user Label, Menu
        *  have title of application
        */
        toolbarPanel.setLayout(null);
        toolbarPanel.setOpaque(true);
        toolbarPanel.setBackground(MyStyle.PrimaryColor);
        toolbarPanel.setBounds(0, 0, MyConstants.ScreenWidth, MyConstants.ToolbarHeight);
        closeButton.setWidthOfView(MyConstants.ScreenWidth);
        
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        userPanel.setOpaque(true);
        userPanel.setBackground(Color.red);
        userPanel.setBounds(MyConstants.ScreenWidth- 100-MyConstants.VerySmallMargin - 300, 0, 300, MyConstants.ToolbarHeight);
        usernameLabel.setText("Duy Nguyen");
        usernameLabel.setFont(MyStyle.MediumLabelFont);
        userPanel.add(usernameLabel);
        
        
        toolbarPanel.add(closeButton);
        toolbarPanel.add(userPanel);

        /* Left Panel
        *  -Course
        *  -Class
        */
        leftPanel.setLayout(null);
        leftPanel.setOpaque(true);
        leftPanel.setBackground(Color.red);
        leftPanel.setBounds(
                MyConstants.VerySmallMargin,
                MyConstants.ToolbarHeight + MyConstants.VerySmallMargin,
                MyConstants.LeftPanelWidth,
                MyConstants.LeftPanelHeight
        );
        
        customTreeView.setBounds(0, 0, MyConstants.LabelWidth, MyConstants.LabelHeight);
        leftPanel.add(customTreeView);
        //
        
        /*Main Panel
        * list of student
        * main infomation
        */
        mainPanel.setLayout(null);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.green);
        mainPanel.setBounds(leftPanel.getX() + leftPanel.getWidth() + MyConstants.VerySmallMargin, leftPanel.getY(), MyConstants.MainPanelWidth, MyConstants.MainPanelHeight);
        /*Info Panel
        * Clear infomation of Object
        * Edit
        */
        infoPanel.setLayout(null);
        infoPanel.setOpaque(true);
        infoPanel.setBackground(Color.yellow);
        infoPanel.setBounds(
                mainPanel.getX()+mainPanel.getWidth()+MyConstants.VerySmallMargin,
                mainPanel.getY(),
                MyConstants.InfoPanelWidth,
                MyConstants.InfoPanelHEight
        );
        /* Bottom Panel
        *  Display all Score of Student
        *  Display Salary of Employee
        *  Display more infomation of a Subject
        */
        bottomPanel.setLayout(null);
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(Color.blue);
        bottomPanel.setBounds(leftPanel.getX() + leftPanel.getWidth() + MyConstants.VerySmallMargin, mainPanel.getY() + mainPanel.getHeight() + MyConstants.VerySmallMargin, MyConstants.BottonPanelWidth, MyConstants.BottomPanelHeight);
        add(container);
        container.add(toolbarPanel);
        container.add(leftPanel);
        container.add(mainPanel);
        container.add(infoPanel);
        container.add(bottomPanel);
    }

    @Override
    public void initCommand() {
    }
}
