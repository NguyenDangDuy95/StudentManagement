/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author DUY
 */
public class CustomTableView extends JTable {

    public CustomTableView() {
        setRowHeight(MyConstants.LabelHeight);

        setFont(MyStyle.TreeLabelFont);
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);
        setBorder(new LineBorder(Color.BLACK, 1));
        TableCellRenderer renderer = getDefaultRenderer(String.class);
        ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) renderer).setBorder(new WindowsBorders.DashedBorder(Color.BLACK));
        TableCellRenderer tcr = getTableHeader().getDefaultRenderer();
        getTableHeader().setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) tcr.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                lbl.setForeground(Color.WHITE);
                if (column == 0) {
                    lbl.setPreferredSize(new Dimension(MyConstants.MainPanelButtonWidth, MyConstants.MainPanelButtonHeight));
                    lbl.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
                } else {
                    if (column == getColumnCount() - 1) {
                        lbl.setPreferredSize(new Dimension(MyConstants.MainPanelButtonWidth, MyConstants.MainPanelButtonHeight));
                    }
                    lbl.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
                }
                lbl.setBackground(MyStyle.PrimaryColor);
                lbl.setFont(new Font(MyStyle.TreeLabelFont.getName(), MyStyle.TreeLabelFont.getStyle(), MyStyle.TreeLabelFont.getSize() + 2));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                return lbl;
            }
        });
    }
}
