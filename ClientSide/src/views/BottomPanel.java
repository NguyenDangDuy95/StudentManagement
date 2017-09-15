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
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import userControls.Control;
import userControls.CustomButton;
import userControls.CustomTableView;
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
    private CustomButton btnSave;
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
        btnSave = new CustomButton("Save", MyStyle.PrimaryColor, MyStyle.SecondaryColor);
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

        jtp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                btnSave.setEnabled(false);
                try {
                    reload();
                } catch (IOException ex) {
                    Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = jtp.getSelectedIndex();
                Batch batch = (Batch) SelectedObject;

                if (index == 0) {
                    Vector<Attendance> data = new Vector();
                    int rowCount = attendanceData.getDataVector().size();
                    int columnCount = attendanceData.getColumnCount();
                    int emptyIndex = 0;
                    Vector dataVector = attendanceData.getDataVector();
                    for (int i = 1; i < columnCount; i++) {
                        for (int j = 0; j < rowCount; j++) {
                            Vector a = (Vector) dataVector.get(j);
                            String b = a.get(i).toString();
                            if (b.equals("")) {
                                if (j == rowCount - 1) {
                                    emptyIndex = i;
                                    break;
                                }
                            } else {
                                System.out.println(b);
                                break;
                            }
                        }
                        if (emptyIndex != 0) {
                            break;
                        }
                    }
                    for (Student std : batch.getStdList()) {
                        int count = 1;
                        while (count < emptyIndex) {
                            Attendance att = new Attendance();
                            att.setStatus(attendanceTable.getValueAt(batch.getStdList().indexOf(std), count).toString());
                            att.setBatchID(batch.getId());
                            att.setStudentID(std.getStudentID());
                            att.setSubjectID(batch.getCurrentSubject().getID());
                            att.setCourseID(batch.getCourseID());
                            data.add(att);
                            count++;
                        }
                    }
                    batch.setAttList(data);
                    BatchController.getInstance().update(batch, "attendance");
                    try {
                        reload();
                    } catch (IOException ex) {
                        Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Vector<Score> scList = new Vector<>();
                    int rowCount = scoreListData.getDataVector().size();
                    int columnCount = scoreListData.getColumnCount();
                    int emptyIndex = 0;
                    Vector data = scoreListData.getDataVector();
                    for (int i = 1; i < columnCount; i++) {
                        for (int j = 0; j < rowCount; j++) {
                            Vector a = (Vector) data.get(j);
                            if (a.get(i) == null||a.get(i).toString().equals("")) {
                                if (j == rowCount - 1) {
                                    emptyIndex = i;
                                    break;
                                }
                            } else {
                                System.out.println(a.get(i).toString());
                                break;
                            }
                        }
                        if (emptyIndex != 0) {
                            System.out.println(emptyIndex);
                            break;
                        }
                    }
                    Vector<Subject> subList = CourseController.getInstance().getCourseByID(batch.getCourseID()).getSubjectList();
                    for (Student std : batch.getStdList()) {
                        int stdIndex = batch.getStdList().indexOf(std);
                        int count = 1;
                        while (count < emptyIndex) {

                            double tScore = 0, pScore = 0;

                            try {
                                if (scoreListTable.getValueAt(stdIndex, count) != null) {
                                    tScore = Double.parseDouble(scoreListTable.getValueAt(stdIndex, count).toString().trim());
                                }

                                if (scoreListTable.getValueAt(stdIndex, count + 1) != null) {
                                    pScore = Double.parseDouble(scoreListTable.getValueAt(stdIndex, count + 1).toString().trim());
                                }
                            } catch (Exception ex) {
                                Control.createCustomDialog(MyConstants.OptionDialogType.Message, "Warning", "Wrong Score");
                                return;
                            }
                            Score sc = new Score();
                            sc.setNumberOfExam(1);
                            sc.setStudentID(std.getStudentID());
                            sc.setSubjectID(subList.get(count / 2).getID());
                            if (scoreListTable.getValueAt(stdIndex, count) != null){
                                sc.setTheoryScore(String.valueOf(tScore));
                            }else{
                                sc.setTheoryScore(null);
                            }
                            if (scoreListTable.getValueAt(stdIndex, count +1 ) != null){
                                sc.setActScore(String.valueOf(pScore));
                            }else{
                                sc.setActScore(null);
                            }
                            scList.add(sc);
                            count += 2;
                        }
                    }
                    batch.setScList(scList);
                    BatchController.getInstance().update(batch, "score");
                    try {
                        reload();
                    } catch (IOException ex) {
                        Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                btnSave.setEnabled(false);
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
            jtp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin * 3, MyConstants.BottonPanelWidth - MyConstants.SmallMargin, MyConstants.BottomPanelHeight - MyConstants.VerySmallMargin * 3);
            createAttendanceTracking();
            jtp.addTab("Attendance Tracking", pnAttendance);
            createScoreList();
            jtp.addTab("Score List", scoreTablePanel);
            jtp.setVisible(false);
            btnSave.setBounds(
                    MyConstants.BottonPanelWidth - MyConstants.VerySmallMargin + 1 - (MyConstants.InfoPanelWidth - MyConstants.SmallMargin) / 2,
                    MyConstants.VerySmallMargin,
                    -1 + (MyConstants.InfoPanelWidth - MyConstants.SmallMargin) / 2,
                    MyConstants.VerySmallMargin * 3);
            btnSave.setVisible(false);
            btnSave.setEnabled(false);
            btnSave.setFont(MyStyle.MediumLabelFont);
            btnSave.setForeground(Color.BLACK);
            add(btnSave);
        } else {
            jtp.setBounds(MyConstants.VerySmallMargin, MyConstants.VerySmallMargin, MyConstants.BottonPanelWidth - MyConstants.SmallMargin, MyConstants.BottomPanelHeight - MyConstants.SmallMargin);
            createCurrentSchedule();
            jtp.addTab("Subject List", subjectList);
            createMyScore();
            jtp.addTab("My Score", myScore);
            jtp.setSelectedIndex(1);
            myScoreData = createMyScoreDataModel();
            jtp.setVisible(true);
        }
        jtp.setFont(MyStyle.TreeLabelFont);
        add(jtp);
    }

    private void createAttendanceTracking() {
        pnAttendance.setLayout(null);
        JScrollPane jsp = new JScrollPane(attendanceTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                    attendanceData.addTableModelListener(new TableModelListener() {
                        @Override
                        public void tableChanged(TableModelEvent e) {
                            btnSave.setEnabled(true);
                        }
                    });
                    scoreListData.addTableModelListener(new TableModelListener() {
                        @Override
                        public void tableChanged(TableModelEvent e) {
                            btnSave.setEnabled(true);
                        }
                    });
                    if (attendanceData.getDataVector().size() == 0 && scoreListData.getDataVector().size() == 0) {
                        jtp.setVisible(false);
                        btnSave.setVisible(false);
                    } else {
                        attendanceTable.setModel(attendanceData);
                        int columnCount = attendanceTable.getColumnModel().getColumnCount();
                        System.out.println(columnCount);
                        for (int i = 0; i < columnCount; i++) {
                            TableColumn column = attendanceTable.getColumnModel().getColumn(i);
                            if (i == 0) {
                                column.setPreferredWidth(MyConstants.MediumMargin * 3);
                            } else {
                                if (columnCount < 17) {
                                    column.setPreferredWidth((MyConstants.BottonPanelWidth - MyConstants.SmallMargin - MyConstants.MediumMargin * 3) / (columnCount - 1));
                                } else {
                                    column.setPreferredWidth(MyConstants.SmallMargin * 3);
                                }
                            }

                        }
                        scoreListTable.setModel(scoreListData);
                        for (int i = 0; i < scoreListTable.getColumnModel().getColumnCount(); i++) {
                            TableColumn columnScore = scoreListTable.getColumnModel().getColumn(i);
                            if (i == 0) {
                                columnScore.setPreferredWidth(MyConstants.MediumMargin * 3);
                            } else {
                                columnScore.setPreferredWidth(MyConstants.SmallMargin * 3);
                            }
                        }
                        jtp.setVisible(true);
                        btnSave.setVisible(true);
                    }
                } else {
                    myScoreData = createMyScoreDataModel();
                    subjectListData = createSubjectListDataModel(batch);
                    if (myScoreData.getDataVector().size() == 0 && subjectListData.getDataVector().size() == 0) {
                        jtp.setVisible(false);
                    } else {
                        myScoreTable.setModel(myScoreData);
                        for (int i = 0; i < myScoreTable.getColumnCount(); i++) {
                            TableColumn columnSub = myScoreTable.getColumnModel().getColumn(i);
                            if (i == 0) {
                                columnSub.setPreferredWidth(MyConstants.MediumMargin * 3);
                            } else {
                                columnSub.setPreferredWidth(MyConstants.SmallMargin * 3);
                            }
                        }
                        subjectListTable.setModel(subjectListData);
                        for (int i = 0; i < subjectListTable.getColumnCount(); i++) {
                            TableColumn columnSub = subjectListTable.getColumnModel().getColumn(i);
                            columnSub.setPreferredWidth((MyConstants.BottonPanelWidth - MyConstants.SmallMargin) / 4);
                        }
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
                    row.add("");
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
