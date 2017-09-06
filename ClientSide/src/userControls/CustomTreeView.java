/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import controllers.CourseController;
import helpers.MyConstants;
import helpers.MyStyle;
import models.Course;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import models.Batch;
import models.Subject;

/**
 *
 * @author Duy
 */
public class CustomTreeView extends JPanel {

    public CustomTreeView() {
        add(createSubjectTree());
    }

    private JTree createSubjectTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Subject List");
        Vector<Course> courses = CourseController.getInstance().get();
        for (Course course : courses) {
            DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course);
            top.add(courseNode);
            DefaultMutableTreeNode semesterINode = new DefaultMutableTreeNode("Semester I");
            DefaultMutableTreeNode semesterIINode = new DefaultMutableTreeNode("Semester II");
            DefaultMutableTreeNode semesterIIINode = new DefaultMutableTreeNode("Semester III");
            DefaultMutableTreeNode semesterIVNode = new DefaultMutableTreeNode("Semester IV");
            courseNode.add(semesterINode);
            courseNode.add(semesterIINode);
            courseNode.add(semesterIIINode);
            courseNode.add(semesterIVNode);
            for (Subject sub : course.getSubjectList()) {
                String semester = sub.getSemester().trim();
                switch (semester) {
                    case "I": {
                        DefaultMutableTreeNode subject = new DefaultMutableTreeNode(sub);
                        semesterINode.add(subject);
                        break;
                    }
                    case "II": {
                        DefaultMutableTreeNode subject = new DefaultMutableTreeNode(sub);
                        semesterIINode.add(subject);
                        break;
                    }
                    case "III": {
                        DefaultMutableTreeNode subject = new DefaultMutableTreeNode(sub);
                        semesterIIINode.add(subject);
                        break;
                    }
                    case "IV": {
                        DefaultMutableTreeNode subject = new DefaultMutableTreeNode(sub);
                        semesterIVNode.add(subject);
                        break;
                    }
                }
            }
        }
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node.getClass().equals(Subject.class)) {
                    Subject sub = (Subject) node.getUserObject();
                    //set sub to the main view
                }
            }
        });
        tree.setBounds(0, 0, MyConstants.LeftPanelWidth, MyConstants.LeftPanelHeight);
        tree.setOpaque(true);
        tree.setBackground(MyStyle.Transparent);
        return tree;
    }

    private JTree createBatchTreeView() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Batch List");
        Vector<Course> courses = CourseController.getInstance().get();
        for (Course course : courses) {
            DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course);
            top.add(courseNode);
            for (Batch batch : course.getBatchList()) {
                DefaultMutableTreeNode batchNode = new DefaultMutableTreeNode(batch);
                courseNode.add(batchNode);
            }
        }
        JTree tree = new JTree(top);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node.getClass().equals(Batch.class)) {
                    Batch sub = (Batch) node.getUserObject();
                    //set studentlist to the main view
                }
            }
        });
        return tree;
    }
}
