/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import test.ClientSideMain;
import views.base.BaseView;

/**
 *
 * @author Duy
 */
public class CustomOptionDialog extends JDialog implements BaseView {

    private MyConstants.OptionDialogType type;
    private JPanel containter;
    private JLabel lbMessage, lbTitle;
    private String title;
    private String message;
    private JButton btnNo, btnYes;
    private boolean isOK = false;
    private boolean isClicked = false;

    public CustomOptionDialog(MyConstants.OptionDialogType type, String title, String message) {
        this.type = type;
        this.title = title;
        this.message = message;
        initView();
        draw();
        initCommand();
        setVisible(true);
    }

    @Override
    public void initView() {
        containter = new JPanel();
        lbMessage = new JLabel();
        lbTitle = new JLabel();

        btnNo = new JButton();
        btnYes = new JButton();

    }

    @Override
    public void draw() {

        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getRootPane().setOpaque(true);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 200));
        if (ClientSideMain.CurrentState.equals("Login")) {
            setBounds(MyConstants.LoginPositionX, MyConstants.LoginPositionY, MyConstants.LoginWidth, MyConstants.LoginHeight);
            containter.setBounds(0, (MyConstants.LoginHeight - MyConstants.OptionPaneHeight) / 2, MyConstants.OptionPaneWidth, MyConstants.OptionPaneHeight);
        } else {
            setBounds(0, MyConstants.ToolbarHeight, MyConstants.ScreenWidth, MyConstants.ScreenHeight - MyConstants.ToolbarHeight);
            containter.setBounds((MyConstants.ScreenWidth - MyConstants.OptionPaneWidth) / 2, (MyConstants.ScreenHeight - MyConstants.OptionPaneHeight) / 2, MyConstants.OptionPaneWidth, MyConstants.OptionPaneHeight);
        }

        containter.setBackground(Color.WHITE);

        containter.setLayout(null);
        add(containter);

        lbTitle.setText(title);
        lbTitle.setBounds(MyConstants.VerySmallMargin, 0, MyConstants.OptionPaneWidth-MyConstants.VerySmallMargin, MyConstants.LabelHeight);
        lbTitle.setFont(MyStyle.BigLabelFont);
        lbTitle.setForeground(MyStyle.PrimaryColor);

        lbMessage.setText(message);
        lbMessage.setBounds(0,
                MyConstants.LabelHeight + MyConstants.SmallMargin,
                MyConstants.OptionPaneWidth,
                MyConstants.LabelHeight
        );
        lbMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lbMessage.setFont(MyStyle.MediumLabelFont);

        btnYes.setBounds(MyConstants.OptionPaneWidth - MyConstants.OptionPaneButtonWidth - MyConstants.SmallMargin,
                MyConstants.OptionPaneHeight - MyConstants.OptionPaneButtonHeight - MyConstants.SmallMargin,
                MyConstants.OptionPaneButtonWidth,
                MyConstants.OptionPaneButtonHeight
        );
        btnYes.setBorderPainted(false);
        btnYes.setFocusPainted(false);
        btnYes.setContentAreaFilled(false);
        btnYes.setFont(MyStyle.MediumLabelFont);
        btnYes.setForeground(Color.WHITE);
        btnYes.setBackground(MyStyle.PrimaryColor);
        btnYes.setOpaque(true);
        
        btnNo.setBounds(btnYes.getX() - MyConstants.OptionPaneButtonWidth - MyConstants.VerySmallMargin,
                MyConstants.OptionPaneHeight - MyConstants.OptionPaneButtonHeight - MyConstants.SmallMargin,
                MyConstants.OptionPaneButtonWidth,
                MyConstants.OptionPaneButtonHeight
        );
        btnNo.setBorderPainted(false);
        btnNo.setFocusPainted(false);
        btnNo.setContentAreaFilled(false);
        btnNo.setFont(MyStyle.MediumLabelFont);
        btnNo.setForeground(Color.WHITE);
        btnNo.setBackground(MyStyle.PrimaryColor);
        btnNo.setOpaque(true);
        if (type == MyConstants.OptionDialogType.Message) {
            btnNo.setVisible(false);
            btnYes.setText(MyConstants.OKText);
        } else {
            btnYes.setText(MyConstants.YesText);
            btnNo.setText(MyConstants.NoText);
        }

        containter.add(lbTitle);
        containter.add(lbMessage);
        containter.add(btnYes);
        containter.add(btnNo);
    }

    @Override
    public void initCommand() {
        btnYes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firePropertyChange("isClicked", true, false);
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnYes.setBackground(MyStyle.PressedColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnYes.setBackground(MyStyle.PrimaryColor);
            }
        });
        btnNo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isClicked = true;
                setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNo.setBackground(MyStyle.PressedColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNo.setBackground(MyStyle.PrimaryColor);
            }
        });
    }
}
