package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddContentGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_addContent;
    private JTextField fld_title;
    private JTextField fld_youtube;
    private JComboBox cmb_quiz;
    private JComboBox cmb_course;
    private JTextArea fld_description;
    private JButton btn_addContent;

    private User user;


    public AddContentGUI(EducatorGUI edGUI, User user) {
        this.user = user;

        add(wrapper);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                edGUI.loadContentModel();
                edGUI.setVisible(true);
            }
        });
        setSize(1200, 600);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_addContent.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_title) || Helper.isFieldEmpty(fld_youtube) || Helper.isFieldEmpty(fld_description)) {
                Helper.showMessage("fill");
            } else {
                Item courseItem = (Item) cmb_course.getSelectedItem();
                Item quizItem = (Item) cmb_quiz.getSelectedItem();

                assert courseItem != null;
                if (CourseContent.add(courseItem.getKey(), this.user.getId(), fld_title.getText(), fld_description.getText(), fld_youtube.getText())) {
                    Helper.showMessage("done");
                    dispose();
                }
            }
        });
        loadComboBoxes();
    }

    public void loadComboBoxes() {
        loadComboCourse();
        loadComboQuiz();
    }

    public void loadComboCourse() {
        for (Course c : Course.getListByUser(this.user.getId())) {
            cmb_course.addItem(new Item(c.getId(), c.getName()));
        }
    }

    public void loadComboQuiz() {
        Item quizItem = (Item) cmb_course.getSelectedItem();
        for (Quiz q : Quiz.getListByCourseID(quizItem.getKey())) {
            cmb_quiz.addItem(new Item(q.getId(), q.getName()));
        }
    }
}
