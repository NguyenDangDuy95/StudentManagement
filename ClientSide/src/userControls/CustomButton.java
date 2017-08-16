
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import javax.swing.JButton;

/**
 *
 * @author Duy
 */
public class CustomButton extends JButton{

    public CustomButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
    public CustomButton(String text)
    {
        setText(text);
        setContentAreaFilled(false);
        setOpaque(true);
    }

    
}
