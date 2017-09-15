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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import models.Batch;
import models.Employee;
import models.Request;
import models.Student;
import userControls.Control;
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
    private CustomButton btnCancel;
    private JScrollPane sp;
    private Border border;
    private JPanel noDataPanel;
    private JLabel lbNoData;
    private boolean isTableChanged;

    public InfoPanel() {
        initView();
        initCommand();
        draw();
    }

    @Override
    public void initView() {
        dataModel = new DefaultTableModel();
        btnSave = new CustomButton("Save", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
        btnCancel = new CustomButton("Cancel", MyStyle.DisableColor, MyStyle.DisableColor);
        isTableChanged = false;
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
        noDataPanel.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.InfoPanelWidth - MyConstants.SmallMargin, MyConstants.InfoPanelHEight - MyConstants.SmallMargin);
        lbNoData.setBounds((noDataPanel.getWidth() - lbNoDataSize.width) / 2, (noDataPanel.getHeight() - lbNoDataSize.height) / 2, lbNoDataSize.width, lbNoDataSize.height);
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
        btnSave.setBorder(new MatteBorder(0, 1, 0, 0, Color.BLACK));
        btnSave.setBounds(sp.getX() + 2 + sp.getWidth() / 2, sp.getY() + sp.getHeight() + MyConstants.VerySmallMargin, -1 + sp.getWidth() / 2, MyConstants.VerySmallMargin * 3);
        btnSave.setVisible(false);
        btnSave.setFont(MyStyle.MediumLabelFont);
        btnSave.setForeground(Color.BLACK);

        btnCancel.setEnabled(true);
        btnCancel.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
        btnCancel.setBounds(sp.getX(), sp.getY() + sp.getHeight() + MyConstants.VerySmallMargin, -1 + sp.getWidth() / 2, MyConstants.VerySmallMargin * 3);
        btnCancel.setVisible(false);
        btnCancel.setFont(MyStyle.MediumLabelFont);
        btnCancel.setForeground(Color.BLACK);

        add(sp);
        add(btnSave);
        add(btnCancel);
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
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isTableChanged) {
                    boolean result = Control.getResult(MyConstants.OptionDialogType.Confirm, "Cancel", "Do you want to cancel");
                    if (result) {
                        SelectedObjectType = "";
                        reloadDataModel();
                    }
                } else {
                    SelectedObjectType = "";
                    reloadDataModel();
                }
            }
        });
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SelectedObjectType.equals(Request.AddMessage) && SelectedObject.getClass().equals(Batch.class)) {
                    Batch batch = (Batch) SelectedObject;
                    int rowCount = table.getRowCount();
                    Vector<String> valueList = new Vector();
                    for (int i = 0; i < rowCount; i++) {
                        Object value = table.getValueAt(i, 1);
                        if (value.toString().equals("")) {
                            Control.createCustomDialog(MyConstants.OptionDialogType.Message, "Warning", "You have to input all information");
                            return;
                        } else {
                            valueList.addElement(value.toString());
                        }
                    }
                    Student std = StudentController.getInstance().getStudentFromDataList(valueList, batch);
                    StudentController.getInstance().add(std);
                }
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
            if (SelectedObjectType.equals(Request.AddMessage) && SelectedObject.getClass().equals(Batch.class)) {
                Batch batch = (Batch) SelectedObject;
                dataModel = StudentController.getInstance().getAddTableModel();
                MainView.IsAdd = true;
            }
            table.setModel(dataModel);
            if (dataModel.getDataVector().size() > 0) {
                btnSave.setVisible(true);
                btnCancel.setVisible(true);
                sp.setVisible(true);
                noDataPanel.setVisible(false);
                dataModel.addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        isTableChanged = true;
                        btnSave.setEnabled(true);
                    }
                });
            } else {
                btnSave.setVisible(false);
                btnCancel.setVisible(false);
                sp.setVisible(false);
                noDataPanel.setVisible(true);
            }
            System.out.println("done");
            repaint();
        }
    }
}
