/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.EmployeeController;
import controllers.StudentController;
import controllers.SubjectController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.util.Vector;
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
import userControls.CustomTableView;

/**
 *
 * @author DUY
 */
public class MainPanel extends JPanel {

    public static Object SelectedObject;
    public static String SelectedObjectType;
    public CustomTableView table;
    private DefaultTableModel dataModel;
    private boolean isSelected;

    public MainPanel() {
        isSelected = false;
        setLayout(null);
        setBorder(new LineBorder(MyStyle.PrimaryColor));
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);
        table = new CustomTableView(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                int index = table.getSelectedRow();
                if (index != -1) {
                    isSelected = true;
                    if (SelectedObjectType.equals("batch")) {
                        //show list of student in batch
                        Batch batch = (Batch) SelectedObject;
                        Vector<Student> stdList = new Vector<>();
                        stdList = batch.getStdList();
                        InfoPanel.SelectedObjectType = Request.StudentObject;
                        InfoPanel.SelectedObject = stdList.get(index);
                    }else if (SelectedObjectType.equals(MyConstants.AdminRole)) {
                        InfoPanel.SelectedObjectType = Request.AdminObject;
                        InfoPanel.SelectedObject = EmployeeController.getInstance().getAdminList().get(index);
                    }else if (SelectedObjectType.equals(MyConstants.TeacherRole)) {
                        InfoPanel.SelectedObjectType = Request.EmployeeObject;
                        InfoPanel.SelectedObject = EmployeeController.getInstance().getTeacherList().get(index);
                    }else{
                        InfoPanel.SelectedObjectType = "";
                    }
                }
            }
        });
        dataModel = new DefaultTableModel();
        //table.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin);
        reload();
        table.setModel(dataModel);
        JScrollPane sp = new JScrollPane();
        sp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin);
        sp.setViewportView(table);
        sp.getViewport().setBackground(MyStyle.BackgroundColor);
        Border border = new EmptyBorder(0, 0, 0, 0);
        sp.setViewportBorder(border);
        
        sp.setBorder(border);
        add(sp);
        //add(table);
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
                System.out.println(dataModel);
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
            } else {

            }
            table.setModel(dataModel);
            repaint();
        }
    }
}
