package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
  private JPanel panel1;
  private JTextField txt_name;
  private JTextField txt_uname;
  private JTextField txt_pass;
  private JButton btn_signUp;

  public SignUpGUI() {
    add(panel1);
    pack();
    setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setResizable(false);
    setVisible(true);
    btn_signUp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(User.add(txt_name.getText(), txt_uname.getText(), txt_pass.getText(), "student")){
          JOptionPane.showMessageDialog(null,"Öğrenci Eklendi","Bilgi",JOptionPane.INFORMATION_MESSAGE);
        }else{
          JOptionPane.showMessageDialog(null,"Öğrenci Eklenemedi","Bilgi",JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }
}
