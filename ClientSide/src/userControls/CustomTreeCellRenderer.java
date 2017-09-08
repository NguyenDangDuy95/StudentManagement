/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyStyle;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author DUY
 */
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    public CustomTreeCellRenderer() {
        setOpaque(true);
        setBackgroundSelectionColor(MyStyle.PrimaryColor);
        setTextSelectionColor(Color.WHITE);
        setBorderSelectionColor(null);
    }

    
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); //To change body of generated methods, choose Tools | Templates.
        String node = (String) ((DefaultMutableTreeNode) value).getUserObject();
        if (node.equals("Subject List") || node.equals("Batch List") || node.equals("Employee List")) {
            Font currentFont = getFont();
            setFont(new Font(currentFont.getName(), Font.BOLD, currentFont.getSize()+5));
        }
        if(node.equals("Aptech")){
            Font currentFont = getFont();
            setFont(new Font(currentFont.getName(), Font.BOLD, currentFont.getSize()+10));
        }else{
            Font currentFont = getFont();
            setFont(new Font(currentFont.getName(), Font.PLAIN, currentFont.getSize()));
        }
        return this;
    }

}
