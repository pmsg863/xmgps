package com.xmgps.framework.restapi.gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gps_hwb on 2015/8/24.
 */
public class ResultTableModle extends AbstractTableModel {

    List<Object[]> result = new ArrayList<>();

    List<String> columnName = new ArrayList<>();

    @Override
    public int getRowCount() {
        return result.size();
    }

    @Override
    public int getColumnCount() {
        if(columnName.size()!=0)
            return columnName.size();
        if (result.size() != 0)
            return result.get(0).length;
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= columnName.size() ||columnName.size()==0)
            return " ";
        else
            return columnName.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] objects = result.get(rowIndex);
        return objects[columnIndex];
    }

    public ResultTableModle setData(List<Object[]> data) {
        this.result = data;
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
        return this;
    }

    public ResultTableModle setColumnName(List<String> data) {
        columnName = data;
        return this;
    }
}
