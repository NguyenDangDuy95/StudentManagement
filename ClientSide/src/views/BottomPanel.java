/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.BatchController;
import controllers.CourseController;
import controllers.SubjectController;
import helpers.MyConstants;
import helpers.MyStyle;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import models.Attendance;
import models.Batch;
import models.Request;
import models.Score;
import models.Student;
import models.Subject;
import models.SubjectBatch;
import test.ClientSideMain;
import userControls.CustomTableView;
import userControls.CustomTreeView;
import userControls.NoArrowScrollBar;
import views.base.BaseView;

/**
 *
 * @author DUY
 */
public class BottomPanel extends JPanel implements BaseView {

    public static String SelectedObjectType;
    public static Object SelectedObject;
    private JTabbedPane jtp;
    private JPanel pnAttendance, scoreTablePanel, subjectList, myScore;
    private DefaultTableModel attendanceData, scoreListData, subjectListData, myScoreData;
    private CustomTableView attendanceTable, scoreListTable, subjectListTable, myScoreTable;

    public BottomPanel() {
        initView();
        initCommand();
        draw();

    }

    @Override
    public void initView() {
        jtp = new JTabbedPane();
        pnAttendance = new JPanel();
        scoreTablePanel = new JPanel();
        subjectList = new JPanel();
        myScore = new JPanel();
        attendanceData = new DefaultTableModel();
        scoreListData = new DefaultTableModel();
        subjectListData = new DefaultTableModel();
        myScoreData = new DefaultTableModel();
        attendanceTable = new CustomTableView() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }
        };
        scoreListTable = new CustomTableView() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }

        };
        subjectListTable = new CustomTableView() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        myScoreTable = new CustomTableView() {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    @Override
    public void initCommand() {
        addPropertyChangeListener("SelectedObject", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    reload();
                } catch (IOException ex) {
                    Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void draw() {
        setOpaque(true);
        setLayout(null);
        setBackground(MyStyle.BackgroundColor);
        setBorder(new LineBorder(MyStyle.PrimaryColor));
        if (ClientSideMain.CurrentUserRole.equals(Request.AdminObject) || ClientSideMain.CurrentUserRole.equals(Request.EmployeeObject)) {
            jtp.setBounds(MyConstants.VerySmallMargin, MyConstants.SmallMargin, MyConstants.BottonPanelWidth - MyConstants.SmallMargin, MyConstants.BottomPanelHeight - MyConstants.VerySmallMargin * 3);
            createAttendanceTracking();
            jtp.addTab("Attendance Tracking", pnAttendance);
            createScoreList();
            jtp.addTab("Score List", scoreTablePanel);
            jtp.setVisible(false);
        } else {
            jtp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.BottonPanelWidth - MyConstants.SmallMargin, MyConstants.BottomPanelHeight - MyConstants.SmallMargin);
            createCurrentSchedule();
            jtp.addTab("Subject List", subjectList);
            createMyScore();
            jtp.addTab("My Score", myScore);
            myScoreData = createMyScoreDataModel();
            if (myScoreData.getDataVector().size() > 0) {
                myScoreTable.setModel(myScoreData);
                TableColumn column = myScoreTable.getColumnModel().getColumn(0);
                column.setPreferredWidth(150);
                myScoreTable.getParent().addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        if (myScoreTable.getColumnCount() < 17) {
                            myScoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        } else {
                            myScoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        }
                    }

                });
                jtp.setVisible(true);
            }else jtp.setVisible(false);
        }

        jtp.setBorder(new LineBorder(MyStyle.PrimaryColor));
        add(jtp);
    }

    private void createAttendanceTracking() {
        pnAttendance.setLayout(null);
        JScrollPane jsp = new JScrollPane(attendanceTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(0, 0, jtp.getWidth(), jtp.getHeight() - MyConstants.VerySmallMargin);
        jsp.getViewport().setBackground(MyStyle.BackgroundColor);
        pnAttendance.add(jsp);

    }

    private void createScoreList() {
        scoreTablePanel.setLayout(null);
        JScrollPane jsp = new JScrollPane(scoreListTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(0, 0, jtp.getWidth(), jtp.getHeight() - MyConstants.VerySmallMargin);
        scoreTablePanel.add(jsp);
    }

    private void createMyScore() {
        myScore.setLayout(null);
        JScrollPane jsp = new JScrollPane(myScoreTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(0, 0, jtp.getWidth(), jtp.getHeight() - MyConstants.VerySmallMargin);
        myScore.add(jsp);
    }

    private void createCurrentSchedule() {
        subjectList.setLayout(null);
        JScrollPane jsp = new JScrollPane(subjectListTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(0, 0, jtp.getWidth(), jtp.getHeight());
        subjectList.add(jsp);
    }

    public void reload() throws IOException, ClassNotFoundException {
        if (SelectedObjectType != null) {
            if (SelectedObjectType.equals(Request.BatchObject)) {
                Batch batch = (Batch) SelectedObject;
                if (ClientSideMain.CurrentUserRole.equals(Request.AdminObject) || ClientSideMain.CurrentUserRole.equals(Request.EmployeeObject)) {
                    attendanceData = createAttendanceDataModel(batch);
                    scoreListData = createScoreListDataModel(batch);
                    if (attendanceData.getDataVector().size() == 0 && scoreListData.getDataVector().size() == 0) {
                        jtp.setVisible(false);
                    } else {
                        attendanceTable.setModel(attendanceData);
                        TableColumn columnAtten = attendanceTable.getColumnModel().getColumn(0);
                        columnAtten.setPreferredWidth(250);
                        attendanceTable.getParent().addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                if (attendanceTable.getColumnCount() < 17) {
                                    attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                } else {
                                    attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                }
                            }

                        });
                        scoreListTable.setModel(scoreListData);
                        TableColumn columnScore = scoreListTable.getColumnModel().getColumn(0);
                        columnScore.setPreferredWidth(250);
                        scoreListTable.getParent().addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                if (scoreListTable.getColumnCount() < 17) {
                                    scoreListTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                } else {
                                    scoreListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                }
                            }

                        });
                        jtp.setVisible(true);
                    }
                } else {
                    myScoreData = createMyScoreDataModel();
                    subjectListData = createSubjectListDataModel(batch);
                    if(myScoreData.getDataVector().size() == 0 && subjectListData.getDataVector().size() == 0) jtp.setVisible(false);
                    else{
                        myScoreTable.setModel(myScoreData);
                        TableColumn column = myScoreTable.getColumnModel().getColumn(0);
                        column.setPreferredWidth((MyConstants.MainPanelWidth - MyConstants.SmallMargin)/7);
                        myScoreTable.getParent().addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                if (myScoreTable.getColumnCount() < 17) {
                                    myScoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                                } else {
                                    myScoreTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                }
                            }

                        });
                        subjectListTable.setModel(subjectListData);
                        for (int i = 0; i < subjectListTable.getColumnCount(); i++) {
                            TableColumn columnSub = subjectListTable.getColumnModel().getColumn(i);
                            if(i == 0){
                                columnSub.setPreferredWidth(3*(MyConstants.MainPanelWidth - MyConstants.SmallMargin)/7);
                            }else columnSub.setPreferredWidth(4*(MyConstants.MainPanelWidth - MyConstants.SmallMargin)/21);
                            
                        }
                        subjectListTable.getParent().addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                subjectListTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                            }

                        });
                        jtp.setVisible(true);
                    }
                }
                repaint();

            }
        }
    }

    private DefaultTableModel createAttendanceDataModel(Batch batch) {
        Vector header = new Vector();
        header.add("Student Name");
        int numberOfLesson = Integer.valueOf(batch.getCurrentSubject().getNumberOfLesson().trim());
        for (int i = 1; i <= numberOfLesson; i++) {
            header.add(String.valueOf(i));
        }
        Vector data = new Vector();
        for (Student std : batch.getStdList()) {
            Vector row = new Vector();
            row.addElement(std.toString());
            Vector<Attendance> checked = new Vector();
            for (Attendance att : batch.getAttList()) {
                if (att.getStudentID().equals(std.getStudentID())) {
                    checked.add(att);
                }
            }
            for (int i = 1; i <= numberOfLesson; i++) {
                if (i <= checked.size()) {
                    row.addElement(checked.get(i - 1).getStatus());
                } else {
                    row.add("N/A");
                }
            }
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    private DefaultTableModel createScoreListDataModel(Batch batch) {
        Vector header = new Vector();
        header.add("Student name");
        Vector data = new Vector();
        Vector<Score> scList = batch.getScList();
        Vector<Subject> subList = CourseController.getInstance().getCourseByID(batch.getCourseID()).getSubjectList();
        for (Subject sub : subList) {
            header.addElement("TS " + sub.getSubjectCode());
            header.addElement("PS " + sub.getSubjectCode());
        }
        for (Student std : batch.getStdList()) {
            Vector row = new Vector();
            row.addElement(std.toString());
            for (Score sc : scList) {
                if (sc.getStudentID().equals(std.getStudentID())) {

                    row.addElement(sc.getTheoryScore());
                    row.addElement(sc.getActScore());
                }
            }
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    private DefaultTableModel createMyScoreDataModel() {
        Student std = (Student) ClientSideMain.CurrentUser;
        Vector header = new Vector();
        Vector numberOfExams = new Vector();
        Vector theoryScore = new Vector();
        Vector practicalScore = new Vector();
        Vector data = new Vector();
        Vector<Score> scList = BatchController.getInstance().getBatchByID(std.getBatch()).getScList();
        Vector<Score> scoreOfStudent = new Vector<>();
        Vector<Subject> subList = CourseController.getInstance().getCourseByID(BatchController.getInstance().getBatchByID(std.getBatch()).getCourseID()).getSubjectList();

        header.add("Properties");
        for (Score sc : scList) {
            if (sc.getStudentID().equals(std.getStudentID())) {
                scoreOfStudent.add(sc);
            }
        }

        for (Subject sub : subList) {
            header.addElement(sub.getSubjectCode());
        }

        numberOfExams.add("Number Of Exams");
        theoryScore.add("Theory Score");
        practicalScore.add("Practical Score");

        for (Score sc : scoreOfStudent) {
            numberOfExams.addElement(sc.getNumberOfExam());
            theoryScore.add(sc.getTheoryScore());
            practicalScore.add(sc.getActScore());
        }

        data.add(numberOfExams);
        data.add(theoryScore);
        data.add(practicalScore);
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }

    private DefaultTableModel createSubjectListDataModel(Batch batch) throws IOException, ClassNotFoundException {
        Vector<SubjectBatch> subList = SubjectController.getSubjectListByBatch(batch.getId());
        Vector header = new Vector();
        header.add("Subject");
        header.add("Teacher");
        header.add("Start Date");
        header.add("End Date");
        Vector data = new Vector();
        for (SubjectBatch sp : subList) {
            Vector row = new Vector();
            row.addElement(sp.getSubject().toString());
            row.addElement(sp.getTeacher().toString());
            row.addElement(sp.getStartDate());
            row.addElement((sp.getEndDate() == null) ? "Learning" : sp.getEndDate().toString());
            data.add(row);
        }
        DefaultTableModel dataModel = new DefaultTableModel(data, header);
        return dataModel;
    }
}
