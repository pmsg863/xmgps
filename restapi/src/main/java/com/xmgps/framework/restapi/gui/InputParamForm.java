package com.xmgps.framework.restapi.gui;

import com.xmgps.framework.restapi.parser.SQLModel;
import com.xmgps.framework.restapi.parser.SQLModelManager;
import com.xmgps.framework.restapi.template.ClassAttribute;
import com.xmgps.framework.restapi.template.Freemarker;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gps_hwb on 2015/8/24.
 */
public class InputParamForm {
    private JButton deployButton;
    private JButton cancelButton;
    private JTextField comXmgpsFrameworkRestapiTextField;
    private JTextField testTextField;
    private JTextField testQueryTextField;
    private JTextField queryTextField;
    private JTextField testTextField1;
    private JTextField GETTextField;
    private JTextField textField7;
    private JPanel modelParams;


    private JFrame frame;
    private SQLModel SQLModel;
    private Freemarker freemarker;

    public JPanel getModelParams() {
        return modelParams;
    }

    public InputParamForm(JFrame frame) {
        this.frame = frame;
        deployButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Map<String, String> input = new HashMap<String, String>();
                input.put("packageName", comXmgpsFrameworkRestapiTextField.getText());
                input.put("className", testTextField.getText());
                input.put("subPath", queryTextField.getText());
                input.put("path", testTextField1.getText());
                input.put("apiType", GETTextField.getText());
                input.put("apiName", testQueryTextField.getText());

                if (freemarker.generateSrc(SQLModel, input))
                    JOptionPane.showMessageDialog(null, "successful", "warning", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "failure", "error", JOptionPane.INFORMATION_MESSAGE);

                ClassAttribute attr = new ClassAttribute(input);
                SQLModelManager.registerModel("/" + attr.getPath() + "/" + attr.getSubPath(), SQLModel, attr);

                super.mouseClicked(e);
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING) );
                frame.setVisible(false);
                frame.dispose();
                super.mouseClicked(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputParamForm");
        frame.setContentPane(new InputParamForm(frame).modelParams);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setSQLModel(SQLModel SQLModel) {
        this.SQLModel = SQLModel;
    }

    public void setFreemarker(Freemarker freemarker) {
        this.freemarker = freemarker;
    }
}
