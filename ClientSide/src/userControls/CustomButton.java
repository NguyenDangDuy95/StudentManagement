
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyStyle;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author Duy
 */
public class CustomButton extends JButton {

    private Color fromColor, toColor, displayFromColor, displayToColor;

    public CustomButton(Color from, Color to) {
        this.fromColor = from;
        this.toColor = to;
        this.displayFromColor = from;
        this.displayToColor = to;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public CustomButton(String text, Color to, Color from) {
        super(text);
        this.fromColor = from;
        this.toColor = to;
        this.displayFromColor = from;
        this.displayToColor = to;
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (b) {
            this.displayFromColor = fromColor;
            this.displayToColor = toColor;
            repaint();
        } else {
            this.displayFromColor = MyStyle.DisableColor;
            this.displayToColor = MyStyle.DisableColor;
            repaint();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Paint gp = new GradientPaint(0, 0, displayFromColor, w, h, displayToColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        super.paintComponent(g);
    }

}
