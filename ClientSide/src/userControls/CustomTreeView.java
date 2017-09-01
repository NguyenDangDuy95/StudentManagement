/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import models.Course;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import models.Batch;
import models.Student;
import models.Subject;

/**
 *
 * @author Duy
 */
public class CustomTreeView extends JPanel implements TreeSelectionListener{
    
    private Vector<Course> _courses;
    public CustomTreeView(Vector<Course> courses) {
        top = new DefaultMutableTreeNode("Course List");
        _courses = courses;
    }
    
    private void getData()
    {
        
    }
    private void createStudentTreeView()
    {
        DefaultMutableTreeNode classList = new DefaultMutableTreeNode("Class List");
        DefaultMutableTreeNode subjectList = new DefaultMutableTreeNode("Subject List");
        for(Course course : _courses)
        {
            DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course);
            classList.add(courseNode);
            subjectList.add(courseNode);
            for(Batch batch : course.getBatchList()){
                DefaultMutableTreeNode batchNode = new DefaultMutableTreeNode(batch);
                courseNode.add(batchNode);
            }
        }
        
        
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
