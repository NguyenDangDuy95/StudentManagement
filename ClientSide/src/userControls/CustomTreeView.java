/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userControls;

import controllers.CourseController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.Color;
import models.Course;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import models.Batch;
import models.Request;
import test.ClientSideMain;
import views.MainPanel;

/**
 *
 * @author Duy
 */
public class CustomTreeView extends JScrollPane {

    public JTree tree;

    public CustomTreeView() {
        setOpaque(true);
        setBackground(MyStyle.BackgroundColor);
        setBorder(new LineBorder(MyStyle.PrimaryColor, 1));
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        initView(ClientSideMain.CurrentUserRole);
        setViewportView(tree);
    }

    private void initView(String role) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Aptech");
        if (role.equals(Request.StudentObject)) {
            top.add(createBatchTreeView());
            top.add(createSubjectTree());
        } else {
            top.add(createBatchTreeView());
            top.add(createSubjectTree());
            top.add(createEmployeeTreeView());
        }
        tree = new JTree(top);
        tree.setFont(MyStyle.TreeLabelFont);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setTextSelectionColor(Color.white);
        renderer.setBackgroundSelectionColor(MyStyle.PrimaryColor);
        renderer.setBorderSelectionColor(MyStyle.PrimaryColor);
        tree.setBorder(new EmptyBorder(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, 0, 0));

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (node == null) //Nothing is selected.  
                {
                    return;
                }
                System.out.println(node.toString());
                Object nodeInfo = node.getUserObject();
                String name = node.toString();

                if (!name.equals("Batch List") && !name.equals("Subject List") && !name.equals("Employee List") && !name.equals("Aptech")) {
                    if (nodeInfo.getClass().equals(Batch.class)) {
                        Batch batch = (Batch) nodeInfo;
                        MainPanel.SelectedObjectType = Request.BatchObject;
                        MainPanel.SelectedObject = batch;
                    } else if (name.equals(MyConstants.AdminRole) || name.equals(MyConstants.TeacherRole)) {
                        MainPanel.SelectedObjectType = node.toString();
                    } else if (nodeInfo.getClass().equals(Course.class)) {
                        Course course = (Course) nodeInfo;
                        MainPanel.SelectedObjectType = Request.CourseObject;
                        MainPanel.SelectedObject = course;
                    } else {
                        MainPanel.SelectedObjectType = Request.SubjectObject + node.toString();
                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                        MainPanel.SelectedObject = (Course) parent.getUserObject();

                    }
                }
            }
        });
    }

    private DefaultMutableTreeNode createSubjectTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Subject List");
        Vector<Course> courses = CourseController.getInstance().get();
        for (Course course : courses) {
            DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(course);
            top.add(courseNode);
            DefaultMutableTreeNode semesterINode = new DefaultMutableTreeNode(MyConstants.FirstSemester);
            DefaultMutableTreeNode semesterIINode = new DefaultMutableTreeNode(MyConstants.SecondSemester);
            DefaultMutableTreeNode semesterIIINode = new DefaultMutableTreeNode(MyConstants.ThirdSemester);
            DefaultMutableTreeNode semesterIVNode = new DefaultMutableTreeNode(MyConstants.FourthSemester);
            courseNode.add(semesterINode);
            courseNode.add(semesterIINode);
            courseNode.add(semesterIIINode);
            courseNode.add(semesterIVNode);
        }
        return top;
    }

    private DefaultMutableTreeNode createBatchTreeView() {
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
        return top;
    }

    private DefaultMutableTreeNode createEmployeeTreeView() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Employee List");
        DefaultMutableTreeNode adminTitleNode = new DefaultMutableTreeNode(MyConstants.AdminRole);
        DefaultMutableTreeNode employeeTitleNode = new DefaultMutableTreeNode(MyConstants.TeacherRole);
        top.add(adminTitleNode);
        top.add(employeeTitleNode);
        return top;
    }
}
