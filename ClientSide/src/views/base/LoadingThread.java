/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.base;

import controllers.BatchController;
import controllers.CourseController;
import controllers.EmployeeController;
import controllers.StudentController;
import models.AccessRole;
/**
 *
 * @author Duy
 */
public class LoadingThread extends Thread {

    private AccessRole accessRole;

    public LoadingThread(AccessRole accessRole) {
        this.accessRole = accessRole;
    }

    @Override
    public void run() {
        if (accessRole == AccessRole.Admin || accessRole == AccessRole.Employee) {
            CourseController.getInstance().load();
            BatchController.getInstance().load();
            StudentController.getInstance().load();
            EmployeeController.getInstance().load();
        }
        else{
            CourseController.getInstance().load();
            BatchController.getInstance().load();
            StudentController.getInstance().load();
        }
    }

}
