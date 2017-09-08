/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import controllers.EmployeeController;
import controllers.StudentController;
import controllers.SubjectController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import models.Batch;
import models.Course;
import models.Employee;
import models.Request;
import models.Student;
import models.Subject;

/**
 *
 * @author DUY
 */
public class MainPanel extends JPanel {

    public static Object SelectedObject;
    public static String SelectedObjectType;
    private JTable table;
    private DefaultTableModel dataModel;

    public MainPanel() {
        setLayout(null);
        setBorder(new LineBorder(MyStyle.PrimaryColor));
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(MyConstants.LabelHeight);
        table.setFont(MyStyle.TreeLabelFont);
        table.setOpaque(true);
        table.setBackground(MyStyle.BackgroundColor);
        TableCellRenderer renderer = table.getDefaultRenderer(String.class);
        ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) renderer).setBorder(new WindowsBorders.DashedBorder(Color.BLACK));
        TableCellRenderer tcr = table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) tcr.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                lbl.setForeground(Color.WHITE);
                lbl.setBackground(MyStyle.PrimaryColor);
                lbl.setBorder(new WindowsBorders.DashedBorder(Color.BLACK));
                lbl.setFont(new Font(MyStyle.TreeLabelFont.getName(), MyStyle.TreeLabelFont.getStyle(), MyStyle.TreeLabelFont.getSize() + 5));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                return lbl;
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    if (SelectedObjectType.equals("batch")) {
                        //show list of student in batch
                        Batch batch = (Batch) SelectedObject;
                        Vector<Student> stdList = new Vector<>();
                        stdList = batch.getStdList();
                        System.out.println(stdList.get(index).toString());
                    }
                    if (SelectedObjectType.equals(MyConstants.AdminRole)) {
                        System.out.println(EmployeeController.getInstance().getAdminList().get(index).toString());
                    }
                    if (SelectedObjectType.equals(MyConstants.TeacherRole)) {
                        System.out.println(EmployeeController.getInstance().getTeacherList().get(index).toString());
                    }
                }
            }
        });
        dataModel = new DefaultTableModel();
        //table.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin);
        reload();
        table.setModel(dataModel);
        JScrollPane sp = new JScrollPane();
        sp.setOpaque(true);
        sp.setBackground(MyStyle.BackgroundColor);
        sp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.MainPanelWidth - MyConstants.SmallMargin, MyConstants.MainPanelHeight - MyConstants.SmallMargin);
        sp.setViewportView(table);
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
