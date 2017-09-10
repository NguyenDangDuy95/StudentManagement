/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.EmployeeController;
import controllers.StudentController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import models.Employee;
import models.Request;
import models.Student;
import userControls.CustomButton;
import userControls.CustomTableView;

/**
 *
 * @author DUY
 */
public class InfoPanel extends JPanel {

    public static Object SelectedObject;
    public static String SelectedObjectType;

    private CustomTableView table;
    private DefaultTableModel dataModel;
    private CustomButton btnSave;

    public InfoPanel() {
        setLayout(null);
        setBorder(new LineBorder(MyStyle.PrimaryColor));
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);
        Vector header = new Vector();
        header.add("Properties");
        header.add("Value");
        Vector data = new Vector();
        dataModel = new DefaultTableModel(data, header);
        btnSave = new CustomButton("SAVE", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
        btnSave.setEnabled(false);
        reloadDataModel();
        table = new CustomTableView() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }

        };
        table.setModel(dataModel);
        
        
        
        
        
        JScrollPane sp = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setViewportView(table);
        sp.getViewport().setBackground(MyStyle.BackgroundColor);
        Border border = new EmptyBorder(0, 0, 0, 0);
        sp.setViewportBorder(border);
        sp.setBorder(border);
        sp.setBounds(
                MyConstants.VerySmallMargin,
                MyConstants.VerySmallMargin,
                MyConstants.InfoPanelWidth - MyConstants.SmallMargin,
                MyConstants.InfoPanelHEight - MyConstants.SmallMargin * 3
        );
        btnSave.setBounds(sp.getX(), sp.getY()+sp.getHeight()+MyConstants.VerySmallMargin, sp.getWidth(), MyConstants.VerySmallMargin*3);
        btnSave.setVisible(false);
        btnSave.setFont(MyStyle.MediumLabelFont);
        btnSave.setForeground(Color.BLACK);
        add(sp);
        add(btnSave);
        addPropertyChangeListener("SelectedObject", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                reloadDataModel();
            }
        });
        
    }

    public void reloadDataModel() {
        btnSave.setEnabled(false);
        if (SelectedObject != null) {
            if (SelectedObjectType.equals(Request.AdminObject) || SelectedObjectType.equals(Request.EmployeeObject)) {
                Employee emp = (Employee) SelectedObject;
                dataModel = EmployeeController.getInstance().getInfoTableModel(emp);

            }
            if (SelectedObjectType.equals(Request.StudentObject)) {
                Student std = (Student) SelectedObject;
                dataModel = StudentController.getInstance().getInfoTableModel(std);
            }
            if (SelectedObjectType.equals("")) {
                Vector header = new Vector();
                header.add("Properties");
                header.add("Value");
                Vector data = new Vector();
                dataModel = new DefaultTableModel(data, header);
            }
            table.setModel(dataModel);
            if(dataModel.getDataVector().size()>0){
                btnSave.setVisible(true);
                dataModel.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        btnSave.setEnabled(true);
                    }
                });
            }else btnSave.setVisible(false);
            System.out.println("done");
            repaint();
        }
    }
}
