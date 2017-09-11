/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.BatchController;
import controllers.EmployeeController;
import controllers.StudentController;
import controllers.SubjectController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Batch;
import models.Course;
import models.Employee;
import models.Request;
import models.Student;
import models.Subject;
import userControls.Control;
import userControls.CustomButton;
import userControls.CustomTableView;
import views.base.BaseView;

/**
 *
 * @author DUY
 */
public class MainPanel extends JPanel implements BaseView {

    public static Object SelectedObject;
    public static String SelectedObjectType;

    private DefaultTableModel dataModel;
    private boolean isSelected, hasData;
    private JScrollPane sp;
    private Border border;
    private CustomButton btnDelete, btnAdd;
    private JPanel noDataPanel;
    private JLabel lbNoData;

    public CustomTableView table;

    public MainPanel() {
        isSelected = false;
        initView();
        initCommand();
        draw();

        //add(table);
    }

    @Override
    public void initView() {
        table = new CustomTableView() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lbNoData = new JLabel("No Data");
        hasData = false;
        dataModel = new DefaultTableModel();
        sp = new JScrollPane();
        border = new EmptyBorder(0, 0, 0, 0);
        noDataPanel = new JPanel();
        btnDelete = new CustomButton("Delete", MyStyle.PrimaryRedColor, MyStyle.SecondaryRedColor);
        btnAdd = new CustomButton("Add", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
    }

    @Override
    public void draw() {
        setLayout(null);
        setBorder(new LineBorder(MyStyle.PrimaryColor));
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);

        lbNoData.setFont(MyStyle.NoDataFont);
        lbNoData.setBackground(MyStyle.BackgroundColor);
        lbNoData.setForeground(MyStyle.DisableColor);
        Dimension lbNoDataSize = lbNoData.getPreferredSize();

        noDataPanel.setLayout(null);
        noDataPanel.setVisible(true);
        noDataPanel.setBackground(MyStyle.BackgroundColor);
        noDataPanel.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin);

        lbNoData.setBounds((noDataPanel.getWidth() - lbNoDataSize.width) / 2, (noDataPanel.getHeight() - lbNoDataSize.height) / 2, lbNoDataSize.width, lbNoDataSize.height);

        noDataPanel.add(lbNoData);

        btnAdd.setVisible(false);
        btnAdd.setFont(MyStyle.MediumLabelFont);
        btnAdd.setBounds(
                MyConstants.MainPanelWidth - MyConstants.VerySmallMargin - MyConstants.MainPanelButtonWidth,
                MyConstants.MainPanelHeight - MyConstants.MediumMargin,
                MyConstants.MainPanelButtonWidth,
                MyConstants.MainPanelButtonHeight
        );

        btnDelete.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setFont(MyStyle.MediumLabelFont);
        btnDelete.setBounds(
                MyConstants.VerySmallMargin,
                MyConstants.MainPanelHeight - MyConstants.MediumMargin,
                MyConstants.MainPanelButtonWidth,
                MyConstants.MainPanelButtonHeight
        );

        sp.setVisible(false);
        sp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin * 3);
        sp.setViewportView(table);
        sp.getViewport().setBackground(MyStyle.BackgroundColor);
        sp.setViewportBorder(border);
        sp.setBorder(border);

        add(btnAdd);
        add(btnDelete);
        add(noDataPanel);
        add(sp);
    }

    @Override
    public void initCommand() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int index = table.getSelectedRow();
                if (index != -1) {
                    isSelected = true;
                    btnDelete.setEnabled(true);
                    if (SelectedObjectType.equals("batch")) {
                        //show list of student in batch
                        Batch batch = (Batch) SelectedObject;
                        Vector<Student> stdList = new Vector<>();
                        stdList = batch.getStdList();
                        InfoPanel.SelectedObjectType = Request.StudentObject;
                        InfoPanel.SelectedObject = stdList.get(index);
                    } else if (SelectedObjectType.equals(MyConstants.AdminRole)) {
                        InfoPanel.SelectedObjectType = Request.AdminObject;
                        InfoPanel.SelectedObject = EmployeeController.getInstance().getAdminList().get(index);
                    } else if (SelectedObjectType.equals(MyConstants.TeacherRole)) {
                        InfoPanel.SelectedObjectType = Request.EmployeeObject;
                        InfoPanel.SelectedObject = EmployeeController.getInstance().getTeacherList().get(index);
                    } else {
                        InfoPanel.SelectedObjectType = "";
                    }
                }
            }
        });
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() > -1) {
                    boolean result = Control.getResult(MyConstants.OptionDialogType.Confirm, "Delete", "Are you sure to delete?");
                    if (result) {
                        int index = table.getSelectedRow();

                        if (SelectedObjectType.equals("batch")) {
                            //show list of student in batch
                            Batch batch = (Batch) SelectedObject;
                            Vector<Student> stdList = new Vector<>();
                            stdList = batch.getStdList();
                            StudentController.getInstance().delete(stdList.get(index));
                        } else if (SelectedObjectType.equals(MyConstants.AdminRole) || SelectedObjectType.equals(MyConstants.TeacherRole)) {
                            EmployeeController.getInstance().delete(EmployeeController.getInstance().get().get(index));
                        } else {
                            InfoPanel.SelectedObjectType = "";
                        }

                    }
                }
            }
        });
    }

    public void reload() {
        if (SelectedObject != null) {
            if (SelectedObjectType.equals(MyConstants.AdminRole)) {
                //show admin list
                Vector<Employee> adminList = new Vector<>();
                for (Employee emp : EmployeeController.getInstance().get()) {
                    if (emp.getPositionName().equals("admin")) {
                        adminList.add(emp);
                    }
                }
                dataModel = EmployeeController.getInstance().getTableModelByList(adminList);

            }
            if (SelectedObjectType.equals(MyConstants.TeacherRole)) {
                // show list of employee
                Vector<Employee> empList = new Vector<>();
                for (Employee emp : EmployeeController.getInstance().get()) {
                    if (emp.getPositionName().equals("teacher")) {
                        empList.add(emp);
                    }
                }
                //create table model
                dataModel = EmployeeController.getInstance().getTableModelByList(empList);
            }
            if (SelectedObjectType.equals("batch")) {
                //show list of student in batch
                Batch batch = (Batch) SelectedObject;
                Vector<Student> stdList = new Vector<>();
                stdList = batch.getStdList();
                dataModel = StudentController.getInstance().getTableModelByList(stdList);
                //System.out.println(batch.getCurrentTeacher().toString());
            }

            if (SelectedObjectType.lastIndexOf(Request.SubjectObject) != -1) {
                String semes = SelectedObjectType.substring(SelectedObjectType.lastIndexOf(Request.SubjectObject)
                        + Request.SubjectObject.length());
                System.out.println(semes);
                //show subject list
                Vector<Subject> subList = new Vector<Subject>();
                Course course = (Course) SelectedObject;
                for (Subject sub : course.getSubjectList()) {
                    if (("Semester " + sub.getSemester().trim()).equals(semes)) {
                        subList.add(sub);
                    }
                }
                //create table model
                dataModel = SubjectController.getTableModelByList(subList);
            }
            if (SelectedObjectType.equals(Request.CourseObject)) {
                Course course = (Course) SelectedObject;
                Vector<Batch> batchList = course.getBatchList();
                dataModel = BatchController.getInstance().getTableModelFromList(batchList);
            }

            if (dataModel.getDataVector().size() > 0) {
                hasData = true;

            } else {
                hasData = false;
            }
            redraw();
        }
    }

    private void redraw() {
        sp.setVisible(hasData);
        btnDelete.setVisible(hasData);
        btnAdd.setVisible(hasData);
        noDataPanel.setVisible(!hasData);
        if (hasData) {
            table.setModel(dataModel);
            table.getColumnModel().getColumn(0).setWidth(MyConstants.SmallMargin * 5);
            table.getColumnModel().getColumn(table.getColumnCount() - 1).setWidth(MyConstants.SmallMargin * 5);
        }
        repaint();
    }
}
