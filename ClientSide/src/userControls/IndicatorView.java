/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import helpers.MyStyle;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Duy
 */
public class IndicatorView extends JPanel{

    public IndicatorView(String state) {
        if(state.equals("Login"))
        {
            createLoginIndicator();
        }else createMainViewIndicator();
    }
    public void showIndicator(){
        setOpaque(true);
        setBackground(MyStyle.IndicatorColor);
        
        setVisible(true);
    }
    public void hideIndicator(){
        
    }
    private void createLoginIndicator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createMainViewIndicator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
