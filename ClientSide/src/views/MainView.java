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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JPanel container, leftPanel, bottomPanel, toolbarPanel,userPanel;
    private MainPanel mainPanel;
    private InfoPanel infoPanel;
    private JScrollPane leftScroll, mainScroll,infoScroll,bottomScroll;
    private CloseButton closeButton;
    private JLabel usernameLabel;
    private CustomTreeView customTreeView;

    public MainView() throws HeadlessException {
        ClientSideMain.CurrentState = "MainView";
        CourseController.getInstance().load();
        System.out.println("Done get Course");
        BatchController.getInstance().load();
        System.out.println("Done get Batch");
        EmployeeController.getInstance().load();
        System.out.println("Done get Employee");
        StudentController.getInstance().load();
        System.out.println("Done get Student");
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        container = new JPanel();
        leftPanel = new JPanel();
        mainPanel = new MainPanel();
        infoPanel = new InfoPanel();
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
        usernameLabel.setText(ClientSideMain.CurrentUser.toString());
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
        customTreeView.setBounds(0, 0, MyConstants.LeftPanelWidth, MyConstants.LeftPanelHeight);
        customTreeView.tree.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                mainPanel.reload();
            }
        });
        leftPanel.add(customTreeView);
        //
        
        /*Main Panel
        * list of student
        * main infomation
        */
        mainPanel.setLayout(null);
        mainPanel.setBounds(leftPanel.getX() + leftPanel.getWidth() + MyConstants.VerySmallMargin, leftPanel.getY(), MyConstants.MainPanelWidth, MyConstants.MainPanelHeight);
        mainPanel.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                infoPanel.reloadDataModel();
                System.out.println("ahihi");
            }
        });
        /*Info Panel
        * Clear infomation of Object
        * Edit
        */
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
