package com.xmgps.framework.restapi.gui;

import com.xmgps.framework.restapi.parser.SQLModelManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gps_hwb on 2015/8/24.
 */
public class DeployForm {
    private JTable table1;
    private JButton deployButton;
    private JButton cancelButton;
    private JPanel viewAPI;

    private JFrame frame;

    public DeployForm(JFrame frame) {
        this.frame = frame;
        deployButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean deploy = SQLModelManager.deploy();
                if ( deploy )
                    JOptionPane.showMessageDialog(null, "successful", "warning", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "failure", "error", JOptionPane.INFORMATION_MESSAGE);

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

    private void createUIComponents() {
        table1 = new JTable();
        table1.setModel(new ResultTableModle().setData(SQLModelManager.getRegisterModels()).setColumnName(Arrays.asList("Path","Type","SQL")));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DeployForm");
        frame.setContentPane(new DeployForm(frame).viewAPI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getViewAPI() {
        return viewAPI;
    }
}
