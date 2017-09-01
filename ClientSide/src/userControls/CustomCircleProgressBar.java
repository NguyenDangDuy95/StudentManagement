/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import javax.swing.JProgressBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ProgressBarUI;

/**
 *
 * @author Duy
 */
public class CustomCircleProgressBar extends JProgressBar{

    @Override
    protected void setUI(ComponentUI newUI) {
        super.setUI(new CircularProgressBarUI());
    }
    
}
