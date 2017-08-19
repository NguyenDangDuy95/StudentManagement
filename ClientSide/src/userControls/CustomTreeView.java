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
import models.Semester;
import models.Subject;

/**
 *
 * @author Duy
 */
public class CustomTreeView extends JPanel implements TreeSelectionListener{
    private DefaultMutableTreeNode top;
    private Vector<Course> _courses;
    private Vector<Subject> _subjects;
    public CustomTreeView(Vector<Course> courses) {
        top = new DefaultMutableTreeNode("Course List");
        _courses = courses;
    }
    
    private void getData()
    {
        
    }
    private void createStudentTreeView(DefaultMutableTreeNode top)
    {
        Vector<DefaultMutableTreeNode> courses = new Vector<DefaultMutableTreeNode>(); 
        for(Course course : _courses)
        {
            DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course.getName());
            top.add(courseNode);
            for(Semester sem : Semester.values())
            {
                DefaultMutableTreeNode semNode = new DefaultMutableTreeNode(sem);
                courseNode.add(semNode);
                for(Subject sub : _subjects)
                {
                    if(sub.getSemester().equals(sem))
                    {
                        DefaultMutableTreeNode subject = new DefaultMutableTreeNode(sub.getName());
                        semNode.add(subject);
                    }
                }
            }
        }       
    }
    
    
    
    
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
