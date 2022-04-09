package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.CourseContent;
import com.patikadev.Model.Quiz;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EducatorGUI extends JFrame {
    private final User user;
    private JPanel wrapper;
    private JLabel lbl_welcomeEducator;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_courses;
    private JScrollPane scrl_courseList;
    private JTable tbl_courseList;
    private JPanel pnl_content;
    private JScrollPane scrl_content;
    private JButton btn_addContent;
    private JTextField fld_updateTitle;
    private JTextArea txtArea_upadateDescription;
    private JTextField fld_updateYoutube;
    private JButton btn_clearSelection;
    private JButton btn_deleteContent;
    private JTextField fld_searchKurs;
    private JTextField fld_searchTitle;
    private JButton btn_search;
    private JButton btn_updateContent;
    private JComboBox cmb_quiz;
    private JTextField fld_updateQuiz;
    private JTable tbl_quizList;
    private JButton btn_deleteQuiz;
    private JButton btn_addQuiz;
    private JTextField fld_addQuizName;
    private JComboBox cmb_addQuizCourse;
    private JLabel lbl_educator_welcome;
    private JButton btn_educator_logout;
    private JTabbedPane tab_educator;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable table1;
    private JPanel pnl_content_list;
    private JTable tbl_contentList;
    private JPanel pnl_quiz_list;
    private JTextField textField1;
    private DefaultTableModel mdl_educator_course_list;
    private Object[] row_educator_course_list;
    private ArrayList<Course> course_list;
    private DefaultTableModel mdl_educator_content_list;
    private Object[] row_educator_content_list;
    private ArrayList<CourseContent> course_content_list = new ArrayList<CourseContent>();


    public EducatorGUI(User user) {
        this.user = user;
        add(wrapper);
        setSize(1000, 800);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcomeEducator.setText("Hoş Geldin: " + user.getName());


        btn_educator_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });


        // CourseList
        mdl_educator_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_educator_course_list = {"ID", "Kullanıcı ID", "Patika ID", "Ders Adı", "Programlama Dili"};
        mdl_educator_course_list.setColumnIdentifiers(col_educator_course_list);
        row_educator_course_list = new Object[col_educator_course_list.length];
        loadCourseModel();

        tbl_courseList.setModel(mdl_educator_course_list);
        tbl_courseList.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_courseList.getTableHeader().setReorderingAllowed(false);

        //## CourseList


        // ContentList
        mdl_educator_content_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] col_educator_content_list = {"ID", "Kurs ID", "Başlık"};
        mdl_educator_content_list.setColumnIdentifiers(col_educator_content_list);
        row_educator_content_list = new Object[col_educator_content_list.length];
        loadContentModel();

        tbl_contentList.setModel(mdl_educator_content_list);
        tbl_contentList.getTableHeader().setReorderingAllowed(false);

        tbl_contentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selectedRow = tbl_contentList.rowAtPoint(point);
                tbl_contentList.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        tbl_contentList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tbl_contentList.getSelectedRow() == -1) {
                    return;
                }

                int id = Integer.parseInt(tbl_contentList.getValueAt(tbl_contentList.getSelectedRow(), 0).toString());
                String title;
                String description;
                String youtube;
                int quiz_id;
                String query = "SELECT title,description,youtube,quiz_id FROM content WHERE id =" + id;

                try {
                    Statement st = DBConnector.getInstance().createStatement();
                    ResultSet rs = st.executeQuery(query);

                    if (rs.next()) {
                        title = rs.getString("title");
                        description = rs.getString("description");
                        youtube = rs.getString("youtube");
                        quiz_id = rs.getInt("quiz_id");

                        fld_updateTitle.setText(title);
                        txtArea_upadateDescription.setText(description);
                        fld_updateYoutube.setText(youtube);
                        fld_updateQuiz.setText(String.valueOf(quiz_id));

                        if (cmb_quiz.getItemCount() == 0) {
                            cmb_quiz.addItem(new Item(0, ""));

                            for (Quiz obj : Quiz.getListByEducatorID(user.getId())) {
                                cmb_quiz.addItem(new Item(obj.getId(), obj.getName()));
                            }
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        cmb_quiz.addActionListener(e -> {
            if (cmb_quiz.getSelectedItem() != null) {
                if (((Item) cmb_quiz.getSelectedItem()).getKey() != 0) {
                    fld_updateQuiz.setText(
                            fld_updateQuiz.getText().split(" ")[0] + " YeniDeğer=>" + String.valueOf(((Item) cmb_quiz.getSelectedItem()).getKey())
                    );
                } else {
                    fld_updateQuiz.setText(fld_updateQuiz.getText().split(" ")[0]);
                }

            }
        });

        btn_addContent.addActionListener(e -> {
            AddContentGUI addContentGUI = new AddContentGUI(this, this.user);
            this.setVisible(false);
        });
        btn_search.addActionListener(e -> {
            String course = fld_searchKurs.getText();
            String contentTitle = fld_searchTitle.getText();
            loadContentModel(course, contentTitle);
        });
    }

    private void loadContentModel(String course, String contentTitle) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contentList.getModel();
        clearModel.setRowCount(0);

        int i;
        for (CourseContent obj : CourseContent.getListByUserForEducatorFilter(this.user.getId(), course, contentTitle)) {
            i = 0;
            row_educator_content_list[i++] = obj.getId();
            row_educator_content_list[i++] = obj.getCourse_id();
            row_educator_content_list[i++] = obj.getTitle();
            mdl_educator_content_list.addRow(row_educator_content_list);
        }
    }

    public void loadContentModel() {
        DefaultTableModel clear = (DefaultTableModel) tbl_contentList.getModel();
        clear.setRowCount(0);

        int i;
        for (CourseContent obj : CourseContent.getListByUserForEducator(user.getId())) {
            i = 0;
            row_educator_content_list[i++] = obj.getId();
            row_educator_content_list[i++] = obj.getCourse_id();
            row_educator_content_list[i++] = obj.getTitle();
            mdl_educator_content_list.addRow(row_educator_content_list);
        }
    }

    private void loadCourseModel() {
        DefaultTableModel clear = (DefaultTableModel) tbl_courseList.getModel();
        clear.setRowCount(0);
        int i;
        course_list = Course.getListByUser(user.getId());
        for (var obj : course_list) {
            i = 0;
            row_educator_course_list[i++] = obj.getName();
            row_educator_course_list[i++] = obj.getLang();
            row_educator_course_list[i++] = obj.getPatika().getName();
            mdl_educator_course_list.addRow(row_educator_course_list);

        }
    }

}