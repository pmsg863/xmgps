package com.xmgps.framework.restapi.gui;

import com.xmgps.framework.restapi.parser.MetaData;
import com.xmgps.framework.restapi.parser.SQLModel;
import com.xmgps.framework.restapi.parser.SQLModelManager;
import com.xmgps.framework.restapi.template.Freemarker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gps_hwb on 2015/8/21.
 */
public class RestGuiForm {
    private JTextField textField1;
    private JButton testButton;
    private JButton deployButton;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JButton configButton;
    private JButton viewDeployButton;
    private JPanel RestApi;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;

    SQLModelManager manager = new SQLModelManager();
    Freemarker freemarker = new Freemarker();

    public RestGuiForm() {
        testButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = textField1.getText();
                if (text == null || text.length() <= 0)
                    JOptionPane.showMessageDialog(null, "Please enter the SQL", "warning", JOptionPane.WARNING_MESSAGE);
                else {
                    try {
                        SQLModel sqlModel = manager.buildSQL(text);
                        TableModle model = (TableModle) table1.getModel();
                        model.setData(sqlModel.getInputParams());
                        model = (TableModle) table2.getModel();
                        model.setData(sqlModel.getOutputParams());

                        List<Object[]> objects = null;
                        switch (sqlModel.type){
                            case "SELECT":
                                objects = SQLModelManager.runSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                                break;
                            case "INSERT":
                                objects = SQLModelManager.updateSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                                break;
                            case "UPDATE":
                                objects = SQLModelManager.updateSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                                break;
                            case "DELETE":
                                objects = SQLModelManager.updateSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                            case "PROC":
                                List<Object[]> select = SQLModelManager.runSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                                List<Object[]> updata = SQLModelManager.updateSQLByList(sqlModel.generateSQL(), sqlModel.getInputParams().stream().map(MetaData::getDefaultValue).collect(Collectors.toList()));
                                if ( select.size()!=0 )
                                    objects = select;
                                if( updata.size()!=0 )
                                    objects = updata;
                                break;
                        }

                        ResultTableModle model2 = (ResultTableModle) table3.getModel();
                        model2.setColumnName(sqlModel.getOutputParams().stream().map(mo->mo.getName()).collect(Collectors.toList())).setData(objects);
                        deployButton.setEnabled(true);

                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.toString(), "warning", JOptionPane.ERROR_MESSAGE);
                    }
                }
                super.mouseClicked(e);
            }
        });
        deployButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SQLModel sqlModel = null;
                try {
                    sqlModel = manager.buildSQL(textField1.getText());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                JFrame frame = new JFrame("InputParamForm");
                InputParamForm inputParamForm = new InputParamForm(frame);
                inputParamForm.setSQLModel(sqlModel);
                inputParamForm.setFreemarker(freemarker);

                frame.setContentPane(inputParamForm.getModelParams());
                
                frame.pack();
                frame.setVisible(true);
                super.mouseClicked(e);
            }
        });

        viewDeployButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("DeployForm");
                frame.setContentPane(new DeployForm(frame).getViewAPI());
                
                frame.pack();
                frame.setVisible(true);
                super.mouseClicked(e);
            }
        });

        configButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("ConfigForm");
                frame.setContentPane(new ConfigForm(frame).getConfig());
                
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        RestGuiForm restGuiForm = new RestGuiForm();
        JFrame frame = new JFrame("RestGuiForm");
        frame.setContentPane(restGuiForm.RestApi);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);

        restGuiForm.textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                restGuiForm.deployButton.setEnabled(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restGuiForm.deployButton.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restGuiForm.deployButton.setEnabled(false);
            }
        });
        try {
            restGuiForm.freemarker.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        table1 = new JTable();
        table1.setModel(new TableModle());
        table2 = new JTable();
        table2.setModel(new TableModle());
        table3 = new JTable();
        table3.setModel(new ResultTableModle());
    }
}
