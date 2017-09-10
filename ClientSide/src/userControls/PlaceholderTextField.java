
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Duy
 */
public class PlaceHolderTextField extends JTextField implements FocusListener{
    private String placeholder;
    private boolean isFocused = false;

    public PlaceHolderTextField() {
        setBorder(new CompoundBorder(new LineBorder(MyStyle.PrimaryColor), new EmptyBorder(0, MyConstants.VerySmallMargin, 0, 0)));
        addFocusListener(this);

    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        if ((getText().trim().equals("")||!getText().isEmpty())&&!isFocused) {
            final Graphics2D g = (Graphics2D) pG;
            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(MyStyle.DisableColor);
            g.drawString(placeholder, MyConstants.VerySmallMargin, pG.getFontMetrics().getMaxAscent() + (MyConstants.TextFieldHeight - pG.getFontMetrics().getHeight()) / 2);
        }

    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    @Override
    public void focusGained(FocusEvent e) {
        isFocused = true;
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        isFocused = false;
        repaint();
    }
}
