package com.xmgps.framework.restapi.gui;

import com.xmgps.framework.restapi.parser.SQLModelManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * Created by gps_hwb on 2015/8/25.
 */
public class ConfigForm {
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel config;

    private JFrame frame;

    public ConfigForm(JFrame frame) {
        this.frame = frame;

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SQLModelManager.getConfig().setDirverClassName(textField1.getText());
                SQLModelManager.getConfig().setUrl(textField2.getText());
                SQLModelManager.getConfig().setUser(textField3.getText());
                SQLModelManager.getConfig().setPassword(textField4.getText());
                SQLModelManager.getConfig().setHostname(textField5.getText());
                SQLModelManager.getConfig().setPort(Integer.valueOf(textField6.getText()));
                SQLModelManager.getConfig().setBasePath(textField7.getText());
                SQLModelManager.writeConfig();
                JOptionPane.showMessageDialog(null, "successful", "warning", JOptionPane.INFORMATION_MESSAGE);
                super.mouseClicked(e);
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                frame.setVisible(false);
                frame.dispose();
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConfigForm");
        frame.setContentPane(new ConfigForm(frame).config);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getConfig() {
        return config;
    }

    private void createUIComponents() {
        textField1 = new JTextField(SQLModelManager.getConfig().getDirverClassName());
        textField2 = new JTextField(SQLModelManager.getConfig().getUrl());
        textField3 = new JTextField(SQLModelManager.getConfig().getUser());
        textField4 = new JTextField(SQLModelManager.getConfig().getPassword());
        textField5 = new JTextField(SQLModelManager.getConfig().getHostname());
        textField6 = new JTextField(String.valueOf(SQLModelManager.getConfig().getPort()));
        textField7 = new JTextField(SQLModelManager.getConfig().getBasePath());
    }
}
