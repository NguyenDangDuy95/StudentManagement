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
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JLabel;
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
import views.base.BaseView;

/**
 *
 * @author DUY
 */
public class InfoPanel extends JPanel implements BaseView {

    public static Object SelectedObject;
    public static String SelectedObjectType;

    private CustomTableView table;
    private DefaultTableModel dataModel;
    private CustomButton btnSave;
    private JScrollPane sp;
    private Border border;
    private JPanel noDataPanel;
    private JLabel lbNoData;

    public InfoPanel() {
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        dataModel = new DefaultTableModel();
        btnSave = new CustomButton("SAVE", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
        table = new CustomTableView() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }

        };
        sp = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        border = new EmptyBorder(0, 0, 0, 0);
        lbNoData = new JLabel("No Data");
        noDataPanel = new JPanel();
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
        noDataPanel.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin,MyConstants.InfoPanelWidth - MyConstants.SmallMargin, MyConstants.InfoPanelHEight - MyConstants.SmallMargin);
        lbNoData.setBounds((noDataPanel.getWidth()-lbNoDataSize.width)/2, (noDataPanel.getHeight() - lbNoDataSize.height)/2, lbNoDataSize.width, lbNoDataSize.height);
        noDataPanel.add(lbNoData);
        
        sp.setVisible(false);
        sp.setViewportView(table);
        sp.getViewport().setBackground(MyStyle.BackgroundColor);
        sp.setViewportBorder(border);
        sp.setBorder(border);
        sp.setBounds(
                MyConstants.VerySmallMargin,
                MyConstants.VerySmallMargin,
                MyConstants.InfoPanelWidth - MyConstants.SmallMargin,
                MyConstants.InfoPanelHEight - MyConstants.SmallMargin * 3
        );
        
        btnSave.setEnabled(false);
        btnSave.setBounds(sp.getX(), sp.getY() + sp.getHeight() + MyConstants.VerySmallMargin, sp.getWidth(), MyConstants.VerySmallMargin * 3);
        btnSave.setVisible(false);
        btnSave.setFont(MyStyle.MediumLabelFont);
        btnSave.setForeground(Color.BLACK);
        
        add(sp);
        add(btnSave);
        add(noDataPanel);
    }

    @Override
    public void initCommand() {
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
            if (dataModel.getDataVector().size() > 0) {
                btnSave.setVisible(true);
                sp.setVisible(true);
                noDataPanel.setVisible(false);
                dataModel.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        btnSave.setEnabled(true);
                    }
                });
            } else {
                btnSave.setVisible(false);
                sp.setVisible(false);
            }
            System.out.println("done");
            repaint();
        }
    }
}
